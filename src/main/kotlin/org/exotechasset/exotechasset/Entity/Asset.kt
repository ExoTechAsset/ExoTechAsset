package org.exotechasset.exotechasset.entity

import org.exotechasset.exotechasset.entity.AssetStatus.*

// TODO: auto determine id
// TODO: record into changelog

open class Asset(
        id: String,
        status: AssetStatus = DEPLOYABLE,
        assignee: String? = null,
        auditDate: Date? = null,
        location: Location? = null,
        changelog: Changelog = Changelog(),
        assetDescription: AssetDescription = AssetDescription("")
) {
    private var id: String = id
    private var status: AssetStatus = status
    private var assignee: String? = assignee
    private var auditDate: Date? = auditDate
    private var location: Location? = location
    private var changelog: Changelog = changelog
    private var assetDescription: AssetDescription = assetDescription
    constructor(
            asset: Asset
    ) : this(
            asset.getId(),
            asset.getStatus(),
            asset.getAssignee(),
            asset.getAuditDate(),
            asset.getLocation(),
            asset.getChangelog(),
        asset.getDescription()
    ) {}

    public fun getId(): String = id
    public fun getStatus(): AssetStatus = this.status
    public fun setStatus(status: AssetStatus) {
        this.status = status
        this.addChangelog("Update Status into ${status.toString()} with ID: $id")
    }

    public fun getAssignee(): String? = this.assignee
    public fun setAssignee(assignee: String?) {
        this.assignee = assignee
        this.addChangelog("Update Status into ${if (assignee != null) assignee else "null"} with ID: $id")
    }

    public fun getAuditDate(): Date? = this.auditDate
    public fun setAuditDate(auditDate: Date?) {
        this.auditDate = auditDate
        this.addChangelog("Audit at ${auditDate.toString()} with ID: $id")
    }

    public fun getLocation(): Location? = this.location
    public fun setLocation(location: Location?) {
        this.location = location
        this.addChangelog("Set location into ${location.toString()} with ID: $id")
    }
    public fun getChangelog(): Changelog = this.changelog
    public fun addChangelog(log: String) {
        this.changelog.add(log)
    }
    public fun getDescription(): AssetDescription = this.assetDescription
    public open fun hasChildren(): Boolean = false
    public open fun getChildrenIdList(): List<String> = emptyList()
    public open fun getChildren(): List<Asset> = emptyList()

    public open fun modify(asset: Asset) {
        require(this.id == asset.id)

        this.status = asset.status
        this.assignee = asset.assignee
        this.auditDate = asset.auditDate
        this.location = asset.location
        this.assetDescription = asset.assetDescription
        this.addChangelog("Modify asset with ID: $id")

        check(asset.status == this.status)
        check(asset.assignee == this.assignee)
        check(asset.auditDate == this.auditDate)
        check(asset.location == this.location)
        check(asset.assetDescription == this.assetDescription)
    }

    public fun audit(date:Date = Date.ofNow()) {
        this.auditDate = date
        this.addChangelog("Audit at ${this.auditDate.toString()} with ID: $id")
        check(date == this.auditDate)
    }

    public open fun addChild(asset: Asset) {
        // TODO: rewrite the exception handling statement
        require(false)
    }

    public open fun removeChild(id: String) {
        // TODO: rewrite the exception handling statement
        require(false)
    }

    public open fun removeChild(asset: Asset) {
        // TODO: rewrite the exception handling statement
        require(false)
    }

     public fun accept(visitor:AbstractVisitor) {
         visitor.visit(this)
     }
}
