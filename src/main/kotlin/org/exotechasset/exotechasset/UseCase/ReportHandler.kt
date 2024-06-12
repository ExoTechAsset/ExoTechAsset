package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Metric
import org.exotechasset.exotechasset.entity.Report
import org.exotechasset.exotechasset.usecase.AssetHandler

// TODO Change AssetList to AssetHandler
class ReportHandler(assetHandler: AssetHandler) {
    private var assetHandler:AssetHandler = assetHandler
    public fun generateReport(reportType: ReportType, metrics: Metric): Report{
        // TODO
        var report: Report
        report = ReportFactory().generate(reportType, metrics)
        assetHandler.accept(report)
        return report
    }
}