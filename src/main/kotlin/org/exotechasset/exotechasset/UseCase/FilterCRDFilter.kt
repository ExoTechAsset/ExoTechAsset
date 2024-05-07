package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter

interface FilterCRDFilter {
    public fun addFilter(filterList: List<Filter>) {
        for (filter in filterList) {
            this.addFilter(filter)
        }
    }

    public fun addFilter(filter: Filter)
    public fun getFilter(index: Int): Filter
    public fun getFilters(): List<Filter>
    public fun removeFilter(index: Int)
    public fun createIterator(): FilterIterator = FilterIterator(this)
}
