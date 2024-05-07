package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter

class FilterFactory(filterList: List<Filter> = emptyList()) {
    val filterList: MutableList<Filter> = filterList.toMutableList()

    public fun create(filterType: FilterType, id: Int? = null): Filter =
            when (filterType) {
                FilterType.AND -> AndFilter(id)
                FilterType.OR -> OrFilter(id)
                FilterType.EQUALS_TO -> EqualsToFilter(id)
                FilterType.GREATER_THAN -> GreaterThanFilter(id)
                FilterType.GREATER_THAN_OR_EQUALS_TO -> GreaterThanOrEqualsToFilter(id)
                FilterType.LESS_THAN -> LessThanFilter(id)
                FilterType.LESS_THAN_OR_EQUALS_TO -> LessThanOrEqualsToFilter(id)
            }
}
