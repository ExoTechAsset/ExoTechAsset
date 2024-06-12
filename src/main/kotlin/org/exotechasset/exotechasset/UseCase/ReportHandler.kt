package org.exotechasset.exotechasset.useCase

import org.exotechasset.exotechasset.entity.Metric
import org.exotechasset.exotechasset.entity.Report
import org.exotechasset.exotechasset.usecase.AssetList

// TODO Change AssetList to AssetHandler
class ReportHandler(assetList: AssetList) {
    private var assetList:AssetList = assetList
    public fun generateReport(reportType: ReportType, metrics: Metric): Report{
        // TODO
        var report: Report
        report = ReportFactory().generate(reportType, metrics)
        assetList.accept(report)
        return report
    }
}