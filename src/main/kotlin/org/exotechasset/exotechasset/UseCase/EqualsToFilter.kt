package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Filter

class EqualsToFilter(filterParameterList: List<FilterParameter>) : Filter()
, FilterCRDFilterParameter {
    private val MAX_FILTER_PARAMETER_SIZE: Int = 2
    private val filterParameterList: MutableList<FilterParameter> =
            filterParameterList.toMutableList()

    constructor() : this(emptyList())

    override public fun meet(assetList: List<Asset>): List<Asset> {
        require(this.filterParameterList.size == this.MAX_FILTER_PARAMETER_SIZE)
        val param1:FilterParameter = this.filterParameterList[0]
        val param2: FilterParameter = this.filterParameterList[1]

        var result: MutableList<Asset> = mutableListOf()
        for (asset in assetList) {
            val get1 = param1.get(asset)
            val get2 = param2.get(asset)
            if ((get1 != null) && (get2 != null) && (get1 == get2)) {
                result.add(asset)
                continue
            }

            val str1 = param1.toString(asset)
            val str2 = param2.toString(asset)
            if ((str1 != null) && (str2 != null) && (str1 == str2)) {
                result.add(asset)
                continue
            }

            val num1 = param1.toNumber(asset)
            val num2 = param2.toNumber(asset)
            if ((num1 != null) && (num2 != null) && (num1 == num2)) {
                result.add(asset)
                continue
            }
        }
        return result
    }

    override public fun addFilterParameter(filterParameter: FilterParameter) {
        require(this.filterParameterList.size < this.MAX_FILTER_PARAMETER_SIZE)
        this.filterParameterList.add(filterParameter)
        check(this.filterParameterList.last() == filterParameter)
    }

    override public fun getFilterParameter(index: Int): FilterParameter =
            this.filterParameterList.get(index)

    override public fun removeFilterParameter(index: Int) {
        this.filterParameterList.removeAt(index)
    }
}
