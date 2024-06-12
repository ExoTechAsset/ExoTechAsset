package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.entity.FilterField

class FilterFactory() {
        public fun create(type: FilterType, field: FilterField, value: Any): Filter =
                        when (type) {
                                FilterType.EQUALS_TO -> EqualsToFilter(field, value)
                                FilterType.NOT_EQUALS_TO -> NotEqualsToFilter(field, value)
                                FilterType.GREATER_THAN -> GreaterThanFilter(field, value)
                                FilterType.GREATER_THAN_OR_EQUALS_TO ->
                                                GreaterThanOrEqualsToFilter(field, value)
                                FilterType.LESS_THAN -> LessThanFilter(field, value)
                                FilterType.LESS_THAN_OR_EQUALS_TO ->
                                                LessThanOrEqualsToFilter(field, value)
                        }

        public fun createWithStr(type: String, field: String, value: String): Filter = this.create(FilterType.of(type), FilterField.of(field), value as Any)
}
