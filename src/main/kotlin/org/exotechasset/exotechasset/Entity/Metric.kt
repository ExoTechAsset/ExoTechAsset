package org.exotechasset.exotechasset.entity

class Metric(metrics: MutableMap<AssetGetBy, Any> = mutableMapOf()) {
    private val metrics = metrics
    public fun addMetrics(assetGetBy: AssetGetBy, any: Any){
        metrics.put(assetGetBy, any)
    }
    public fun getMetrics(): MutableMap<AssetGetBy, Any>{
        return metrics
    }
}