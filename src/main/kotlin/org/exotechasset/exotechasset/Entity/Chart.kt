package org.exotechasset.exotechasset.entity
import org.json.JSONObject


abstract class Chart(metrics: Metric = Metric(), datas: JSONObject = JSONObject()): Report(metrics) {
    protected var datas: JSONObject = datas
}