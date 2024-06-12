package org.exotechasset.exotechasset.usecase
import org.exotechasset.exotechasset.entity.*


class ReportFactory {
    public fun generate(reportType: ReportType, metrics:Metric = Metric()) : Report{
        val report: Report
        when(reportType){
            ReportType.TABLE -> report = Table(metrics)
            ReportType.BAR -> report = BarChart(metrics)
            ReportType.PIE -> report = PieChart(metrics)
            ReportType.CSV -> report = CsvVisitor()
        }
        return report
    }
}