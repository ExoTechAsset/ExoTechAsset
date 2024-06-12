package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.entity.FilterField

class LessThanOrEqualsToFilter(field: FilterField, value: Any) : Filter(field, value) {
    override public fun meet(assetList: List<Asset>): List<Asset> {
        var result: MutableList<Asset> = mutableListOf()
        for (asset in assetList) {
            // asset
            val assetValue = FilterValue(field).toNumber(asset)
            if (assetValue == null) {
                continue
            }
            // value
            var valueNumber:Long
            if (value is String) {
                valueNumber = value.toLong()
            } else if (value is Number) {
                valueNumber = value.toLong()
            } else {
                continue
            }
            if (assetValue <= valueNumber) {
                result.add(asset)
            }
        }
        return result
    }
}
