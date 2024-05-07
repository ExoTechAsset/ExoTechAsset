package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter

class FilterFactory(filterList: List<Filter> = emptyList()) {
    val filterList: MutableList<Filter> = filterList.toMutableList()

    public fun create(filterType: FilterType): Filter =
            when (filterType) {
                FilterType.AND -> AndFilter()
                FilterType.OR -> OrFilter()
                FilterType.EQUALS_TO -> EqualsToFilter()
                FilterType.GREATER_THAN -> GreaterThanFilter()
                FilterType.GREATER_THAN_OR_EQUALS_TO -> GreaterThanOrEqualsToFilter()
                FilterType.LESS_THAN -> LessThanFilter()
                FilterType.LESS_THAN_OR_EQUALS_TO -> LessThanOrEqualsToFilter()
            }
}
