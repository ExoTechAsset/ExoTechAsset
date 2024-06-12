package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.AbstractVisitor

class CsvVisitor: AbstractVisitor {
    var result:String = "id,status,assignee,auditDate,location,description,parentId\n"

    override fun visit(asset:Asset) {
        this.result += "${asset.getId()},${asset.getStatus().toString()},${asset.getAssignee()},${asset.getAuditDate().toString()},${asset.getLocation()?.get()},${asset.getDescription()},${asset.getParentId()}\n"
    }
    override fun get() = this.result
}
