package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.entity.EmptyFilter
import org.exotechasset.exotechasset.entity.CompositeAsset

open class FilterIterator {
    protected var filterIterator: Iterator<Filter> = listOf<Filter>().iterator()
    protected var filter: Filter = EmptyFilter

    public constructor(filterParameter:FilterCRDFilterParameter) {
        this.filterIterator = emptyList<Filter>().iterator()
    }

    public constructor(filter:FilterCRDFilter) {
        this.filterIterator = filter.getFilters().iterator()
    }

    public constructor(filter:Filter) {
        if (filter is FilterCRDFilter) {
            this.filterIterator = filter.getFilters().iterator()
        } else {
            this.filterIterator = emptyList<Filter>().iterator()
        }
    }

    public open fun next() {
        this.filter = this.filterIterator.next()
    }

    public open fun hasNext() = this.filterIterator.hasNext()

    public open fun getValue():Filter = this.filter
}
