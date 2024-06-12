import org.exotechasset.exotechasset.entity.AssetGetBy

class ReportRequest(private var reportType: String = "table", private var metrics: MutableMap<String, String> = mutableMapOf()){
    fun invertMetrics(){
        val invertedMetrics = mutableMapOf<String, String>()
        for ((key, value) in metrics){
            invertedMetrics[value] = key
        }
        metrics = invertedMetrics
    }
    fun getMetrics(): MutableMap<String, String> {
        if(metrics.containsKey("x")){
            invertMetrics()
        }
        return metrics
    }
    fun getReportType(): String {
        return reportType
    }

    fun setReportType(reportType: String) {
        this.reportType = reportType
    }
}