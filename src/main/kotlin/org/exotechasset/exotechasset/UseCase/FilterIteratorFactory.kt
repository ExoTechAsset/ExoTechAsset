package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter

class FilterIteratorFactory(val filter: Filter) {
    public fun create(
            filterIteratorType: FilterIteratorType = FilterIteratorType.HIERARCHY_FILTER_ITERATOR
    ): FilterIterator =
            when (filterIteratorType) {
                FilterIteratorType.HIERARCHY_FILTER_ITERATOR -> FilterHierarchyIterator(filter as FilterCRDFilter)
                FilterIteratorType.SIMPLE_FILTER_ITERATOR -> FilterIterator(filter as FilterCRDFilterParameter)
            }
}
