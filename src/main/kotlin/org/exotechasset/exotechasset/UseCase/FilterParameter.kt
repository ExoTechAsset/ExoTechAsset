package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Asset

data class FilterParameter(val by: FilterParameterBy, val value: Any?) {
    public fun get(asset: Asset): Any? =
            when (this.by) {
                FilterParameterBy.VALUE -> value
                FilterParameterBy.ID -> asset.getId()
                FilterParameterBy.STATUS -> asset.getStatus()
                FilterParameterBy.ASSIGNEE -> asset.getAssignee()
                FilterParameterBy.AUDIT_DATE -> asset.getAuditDate()
                FilterParameterBy.LOCATION -> asset.getLocation()
                // FilterParameterBy.CHANGELOG -> asset.getChangelog()
                // FilterParameterBy.PARENT_ID -> asset.
                // FilterParameterBy.CHILDREN -> asset.getChildren()
            }

    public fun toNumber(asset: Asset): Long? =
            when (this.by) {
                FilterParameterBy.VALUE -> value as? Long
                FilterParameterBy.ID -> null
                FilterParameterBy.STATUS -> null
                FilterParameterBy.ASSIGNEE -> null
                FilterParameterBy.AUDIT_DATE -> asset.getAuditDate()?.get()
                FilterParameterBy.LOCATION -> null
                // FilterParameterBy.CHANGELOG -> null
                // FilterParameterBy.PARENT_ID -> asset.
                // FilterParameterBy.CHILDREN -> null
            }

    public fun toString(asset: Asset): String? =
            when (this.by) {
                FilterParameterBy.VALUE -> value as String?
                FilterParameterBy.ID -> asset.getId()
                FilterParameterBy.STATUS -> asset.getStatus().toString()
                FilterParameterBy.ASSIGNEE -> asset.getAssignee()
                FilterParameterBy.AUDIT_DATE -> asset.getAuditDate()?.toString()
                FilterParameterBy.LOCATION -> asset.getLocation()?.get()
                // FilterParameterBy.CHANGELOG -> 
                // FilterParameterBy.PARENT_ID -> asset.
                // FilterParameterBy.CHILDREN -> asset.getChildren()
            }
}
