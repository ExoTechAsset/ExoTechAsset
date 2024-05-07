package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.ToStringInterface

enum class FilterIteratorType : ToStringInterface {
    HIERARCHY_FILTER_ITERATOR {
        override public fun toString(): String = "Hierarchy"
    },
    SIMPLE_FILTER_ITERATOR {
        override public fun toString(): String = "Simple"
    },
}
