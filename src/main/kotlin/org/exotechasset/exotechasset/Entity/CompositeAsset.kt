package org.exotechasset.exotechasset.entity

import org.exotechasset.exotechasset.entity.AssetStatus.*

class CompositeAsset(
        id: String,
        status: AssetStatus = DEPLOYABLE,
        assignee: String? = null,
        auditDate: Date? = null,
        location: Location? = null,
        changelog: Changelog = Changelog()
) : Asset(id, status, assignee, auditDate, location, changelog) {
    private var children: MutableMap<String, Asset> = mutableMapOf()

    public override fun getChildrenIdList(): List<String> = this.children.keys.toList()
    public override fun getChildren(): List<Asset> = this.children.values.toList()

    public override fun hasChildren(): Boolean = (this.children.isEmpty() == false)

    public override fun addChild(asset: Asset) {
        val newAssetId: String = asset.getId()
        this.children.put(newAssetId, asset)

        check(asset == this.children.get(newAssetId))
        this.addChangelog("Added child asset with ID: $newAssetId")
    }

    public override fun removeChild(id: String) {
        require(this.children.containsKey(id))

        this.children.remove(id)

        check(this.children.containsKey(id) == false)
        this.addChangelog("Removed child asset with ID: $id")
    }

    public override fun removeChild(asset: Asset) {
        val id = asset.getId()
        this.removeChild(id)
        this.addChangelog("Removed child asset with ID: $id")
    }

    public override fun modify(asset: Asset) {
        super.modify(asset)
        val newChildren = asset.getChildren()
        val newChildrenMap = newChildren.map { it.getId() to it }.toMap().toMutableMap()
        this.children = newChildrenMap

        check(this.getChildrenIdList().equals(asset.getChildrenIdList()))
        check(this.getChildren().equals(asset.getChildren()))
        this.addChangelog("Modify asset with ID: ${asset.getId()}")
    }
}
