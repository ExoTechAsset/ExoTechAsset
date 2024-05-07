package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.entity.ToStringInterface

enum class FilterType : ToStringInterface {
    AND {
        override public fun toString(): String = "And"
    },
    OR {
        override public fun toString(): String = "Or"
    },
    EQUALS_TO {
        override public fun toString(): String = "EqualsTo"
    },
    GREATER_THAN {
        override public fun toString(): String = "GreaterThan"
    },
    GREATER_THAN_OR_EQUALS_TO {
        override public fun toString(): String = "GreaterThanOrEqualsTo"
    },
    LESS_THAN {
        override public fun toString(): String = "LessThan"
    },
    LESS_THAN_OR_EQUALS_TO {
        override public fun toString(): String = "LessThanOrEqualsTo"
    };

    companion object {
        public fun of(filter: Filter): FilterType =
                when (filter) {
                    is AndFilter -> FilterType.AND
                    is OrFilter -> FilterType.OR
                    is EqualsToFilter -> FilterType.EQUALS_TO
                    is GreaterThanFilter -> FilterType.GREATER_THAN
                    is GreaterThanOrEqualsToFilter -> FilterType.GREATER_THAN_OR_EQUALS_TO
                    is LessThanFilter -> FilterType.LESS_THAN
                    is LessThanOrEqualsToFilter -> FilterType.LESS_THAN_OR_EQUALS_TO
                    else -> throw IllegalArgumentException("Unknown filter type")
                }
    }
}
