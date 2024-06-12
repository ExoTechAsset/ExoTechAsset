package org.exotechasset.exotechasset.Adapter

import ReportRequest
import org.exotechasset.exotechasset.adapter.ServiceController
import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.AssetGetBy
import org.exotechasset.exotechasset.entity.Metric
import org.exotechasset.exotechasset.entity.Report
import org.exotechasset.exotechasset.usecase.ReportHandler
import org.exotechasset.exotechasset.usecase.ReportType
import org.exotechasset.exotechasset.usecase.AssetHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReportController {
    @PostMapping("/generate-report")
    fun generateReport(@RequestBody reportRequest: ReportRequest): String {
        val assetHandler = ServiceController.assetHandler
        val metricMap = reportRequest.getMetrics()
        val metrics = Metric(mutableMapOf())

        // 遍歷 metricMap 中的每個鍵值對，並將其添加到 metrics 中
        for ((key, value) in metricMap) {
            when (key) {
                "id" -> metrics.addMetrics(AssetGetBy.ID, value)
                "status" -> metrics.addMetrics(AssetGetBy.STATUS, value)
                "location" -> metrics.addMetrics(AssetGetBy.LOCATION, value)
                "assignee" -> metrics.addMetrics(AssetGetBy.ASSIGNEE, value)
                "auditDate" -> metrics.addMetrics(AssetGetBy.AUDITDATE, value)
            }
        }


        val reportType = reportRequest.getReportType()
        val reportHandler = ReportHandler(assetHandler)
        val report: Report = when (reportType) {
            "table" -> reportHandler.generateReport(ReportType.TABLE, metrics)
            "barChart" -> reportHandler.generateReport(ReportType.BAR, metrics)
            "pieChart" -> reportHandler.generateReport(ReportType.PIE, metrics)
            else -> reportHandler.generateReport(ReportType.TABLE, metrics)
        }

        return report.get().toString()
    }

}