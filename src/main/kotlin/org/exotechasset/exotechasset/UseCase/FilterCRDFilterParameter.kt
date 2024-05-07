package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter

interface FilterCRDFilterParameter {
    public fun addFilterParameter(filterParameterList: List<FilterParameter>) {
        for (filterParameter in filterParameterList) {
            this.addFilterParameter(filterParameter)
        }
    }

    public fun addFilterParameter(filterParameter: FilterParameter)
    public fun getFilterParameter(index: Int): FilterParameter

    public fun getFilterParameters(): List<FilterParameter>

    public fun removeFilterParameter(index: Int)

    public fun createIterator(
            filterIteratorType: FilterIteratorType = FilterIteratorType.SIMPLE_FILTER_ITERATOR
    ): FilterIterator = FilterIteratorFactory(this as Filter).create(filterIteratorType)
}
