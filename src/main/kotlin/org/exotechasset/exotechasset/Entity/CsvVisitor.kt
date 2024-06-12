package org.exotechasset.exotechasset.entity

//import org.json.JSONArray
//import org.json.JSONObject

class CsvVisitor(metric: Metric = Metric()): Report(metric) {
    private var datas: String = "id, status, assignee, auditDate, location, changelog\n"
    // TODO 未來會把實作依照依賴反轉原則抽離到FrameworkDriver層
    public override fun visit(asset:Asset){
        var data:String = ""
        data += "${asset.getId()}, "
        data += "${asset.getStatus()}, "
        data += "${asset.getAssignee()}, "
        data += "${asset.getAuditDate()}, "
        data += "${asset.getLocation()?.get() ?: Location("").get()}, "
        data += "${asset.getChangelog().get()}\n"
        this.datas += data
    }
    public override fun get() :String{
        return this.datas
    }
}