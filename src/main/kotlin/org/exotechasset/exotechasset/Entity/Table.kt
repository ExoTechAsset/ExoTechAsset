package org.exotechasset.exotechasset.entity

import org.json.JSONArray
import org.json.JSONObject

class Table(metrics: Metric): Report(metrics){
    private var datas: JSONArray = JSONArray()
    public override fun visit(asset:Asset){
        val data = JSONObject()
        for(metric in metrics.getMetrics()){
            when(metric.key){
                AssetGetBy.ID -> {
                    data.accumulate("id", asset.getId())
                }
                AssetGetBy.STATUS -> {
                    data.accumulate("status", asset.getStatus().toString())
                }
                AssetGetBy.ASSIGNEE -> {
                    data.accumulate("assignee", asset.getAssignee().toString())
                }
                AssetGetBy.AUDITDATE -> {
                    data.accumulate("auditDate", asset.getAuditDate().toString())
                }
                AssetGetBy.LOCATION -> {
                    data.accumulate("location", asset.getLocation()?.get() ?: Location("").get())
                }
                AssetGetBy.CHANGELOG -> {
                    data.accumulate("changelog", asset.getChangelog().get())
                }
            }
        }
        this.datas.put(data)
    }
    public override fun get(): JSONArray{
        return this.datas
    }

//    public fun toJSONObject(): JSONObject {
//        val json = JSONObject()
//        for(data in this.datas){
//            for(metric in metrics.getMetrics()) {
//                when(metric.key){
//                    AssetGetBy.ID -> json.put("id", data)
//                    AssetGetBy.STATUS -> json.put("status", data)
//                    AssetGetBy.ASSIGNEE -> json.put("assignee", data)
//                    AssetGetBy.AUDITDATE -> json.put("auditDate", data)
//                    AssetGetBy.LOCATION -> json.put("location", data)
//                    AssetGetBy.CHANGELOG -> json.put("changelog", data)
//                }
//            }
//        }
//        return json
//    }

}