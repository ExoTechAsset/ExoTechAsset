package org.exotechasset.exotechasset.entity

import org.json.JSONObject

class PieChart (metrics: Metric = Metric(), datas: JSONObject =
                    JSONObject()): Chart(metrics, datas) {
    public override fun visit(asset:Asset){
        for(metric in super.metrics.getMetrics()){
            var key:String = ""
            when(metric.key){
                AssetGetBy.ID -> key = asset.getId()
                AssetGetBy.STATUS -> key = asset.getStatus().toString()
                AssetGetBy.ASSIGNEE -> key = asset.getAssignee().toString()
                AssetGetBy.AUDITDATE -> key = asset.getAuditDate().toString()
                AssetGetBy.LOCATION -> key = asset.getLocation()?.get() ?: Location("").get()
                AssetGetBy.CHANGELOG -> key = asset.getChangelog().get().toString()
            }
            if(super.datas.has(key)){
                val value = datas.get(key).toString().toInt() + 1
                super.datas.put(key, value)
            }else{
                super.datas.put(key, 1)
            }
        }
    }
    public override fun get(): JSONObject {
        return datas
    }
}