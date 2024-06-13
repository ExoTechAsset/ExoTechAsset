package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.entity.FilterField

class NotEqualsToFilter(field: FilterField, value: Any) : Filter(field, value) {
    override public fun meet(assetList: List<Asset>): List<Asset> {
        var result: MutableList<Asset> = mutableListOf()
        for (asset in assetList) {
            // asset
            val assetStr = FilterValue(field).toString(asset)
            // value
            if (!(value is String)) {
                continue
            }
            var valueStr = value
            if (assetStr != valueStr) {
                result.add(asset)
            }
        }
        return result
    }
}
