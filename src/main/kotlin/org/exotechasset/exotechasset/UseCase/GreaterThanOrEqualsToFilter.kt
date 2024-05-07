package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Filter

class GreaterThanOrEqualsToFilter(filterParameterList: List<FilterParameter>, id: Int? = null) : Filter(id), FilterCRDFilterParameter {
    private val MAX_FILTER_PARAMETER_SIZE: Int = 2
    private val filterParameterList: MutableList<FilterParameter> =
            filterParameterList.toMutableList()

    constructor() : this(emptyList())
    constructor(id: Int? = null) : this(emptyList(), id)

    override public fun meet(assetList: List<Asset>): List<Asset> {
        require(this.filterParameterList.size == this.MAX_FILTER_PARAMETER_SIZE)
        val param1: FilterParameter = this.filterParameterList[0]
        val param2: FilterParameter = this.filterParameterList[1]

        var result: MutableList<Asset> = mutableListOf()
        for (asset in assetList) {
            val get1 = param1.toNumber(asset)
            val get2 = param2.toNumber(asset)
            if ((get1 == null) || (get2 == null)) {
                continue
            }

            val num1: Long = get1
            val num2: Long = get2
            if (num1 >= num2) {
                result.add(asset)
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

    override public fun getFilterParameters(): List<FilterParameter> =
            this.filterParameterList.toList()

    override public fun removeFilterParameter(index: Int) {
        this.filterParameterList.removeAt(index)
    }
}
