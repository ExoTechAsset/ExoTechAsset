package org.exotechasset.exotechasset.Entity
import org.exotechasset.exotechasset.Entity.Report
import org.exotechasset.exotechasset.UseCase.AssetGetBy

abstract class Chart(metrics: Metric): Report(metrics) {
    protected var datas: MutableMap<String, MutableList<Any>> = mutableMapOf<String, MutableList<Any>>()
    public override fun get(): MutableMap<String, MutableList<Any>>{
        // Todo
        return datas
    }

    public override fun visit(asset: Asset) {
        for(metric in metrics.getMetrics()){
            when(metric.key){
                AssetGetBy.ID -> {
                    val value = datas["ID"]
                    value.add(asset.getId())
                    datas.put("ID",value)
                }
                AssetGetBy.STATUS -> {
                    val value = datas["Status"]
                    value.add(asset.getStatus())
                    datas.put("Status",value)
                }
                AssetGetBy.ASSIGNEE -> {
                    val value = datas["Assignee"]
                    value.add(asset.getAssignee())
                    datas.put("Assignee",value)
                }
                AssetGetBy.AUDITDATE -> {
                    val value = datas["Audit Date"]
                    value.add(asset.getAuditDate())
                    datas.put("Audit Date",value)
                }
                AssetGetBy.LOCATION -> {
                    val value = datas["Location"]
                    value.add(asset.getLocation())
                    datas.put("Location",value)
                }
                AssetGetBy.CHANGELOG -> {
                    val value = datas["Changelog"]
                    value.add(asset.getChangelog())
                    datas.put("Changelog",value)
                }
            }
        }
    }
}