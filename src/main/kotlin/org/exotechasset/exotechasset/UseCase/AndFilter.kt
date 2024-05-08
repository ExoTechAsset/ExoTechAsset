package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Filter

class AndFilter(filterList: List<Filter>, id: Int? = null) : Filter(id), FilterCRDFilter {
    private var filterList: MutableList<Filter> = filterList.toMutableList()

    constructor() : this(emptyList())
    constructor(id: Int? = null) : this(emptyList(), id)

    override public fun modify(filter: Filter) {
        require(filter is AndFilter)
        this.filterList = filter.filterList.toList().toMutableList()
        check(this.filterList.equals(filter.filterList))
    }

    override public fun meet(assetList: List<Asset>): List<Asset> {
        var result: List<Asset> = assetList
        for (filter in this.filterList) {
            result = filter.meet(result)
        }
        return result
    }

    override public fun addFilter(filter: Filter) {
        this.filterList.add(filter)
    }

    override public fun getFilters(): List<Filter> = this.filterList.toList()

    override public fun getFilter(index: Int): Filter = this.filterList.get(index)

    override public fun removeFilter(index: Int) {
        this.filterList.removeAt(index)
    }
}
