package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.ToStringInterface

enum class FilterParameterBy : ToStringInterface {
    VALUE {
        override public fun toString(): String = "Value"
    },
    ID {
        override public fun toString(): String = "Id"
    },
    STATUS {
        override public fun toString(): String = "Status"
    },
    ASSIGNEE {
        override public fun toString(): String = "Assignee"
    },
    AUDIT_DATE {
        override public fun toString(): String = "AuditDate"
    },
    LOCATION {
        override public fun toString(): String = "Location"
    },
    // CHANGELOG,
    // PARENT_ID,
    // CHILDREN,
}
