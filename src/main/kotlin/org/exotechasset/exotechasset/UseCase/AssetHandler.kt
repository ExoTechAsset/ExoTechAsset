package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.AbstractVisitor
import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.CompositeAsset
import org.exotechasset.exotechasset.entity.Date

class AssetHandler {
    private var assetList: AssetList = AssetList()

    public constructor() {}

    public constructor(assetList: AssetList) {
        // create a shallow copy for assetList:MutableMap
        this.assetList = AssetList(assetList)
    }

    public constructor(assetList: List<Asset>) {
        // create a shallow copy for assetList:MutableMap
        this.assetList = AssetList(assetList)

    }

    public fun size() = this.assetList.size()

    public fun addNewAsset(asset: Asset, parentId: String? = null): Boolean = this.assetList.addNewAsset(asset, parentId)

    public fun getAsset(id: String): Asset? = this.assetList.getAsset(id)

    public fun modifyAsset(asset: Asset): Boolean = this.assetList.modifyAsset(asset)

    public fun deleteAsset(id: String) {
        this.assetList.deleteAsset(id)
    }

    public fun deleteAsset(asset: Asset) {
        this.assetList.deleteAsset(asset)
    }

    public fun auditAsset(id: String, date: Date = Date.ofNow()) {
        this.assetList.auditAsset(id, date)
    }

    public fun getChildren(): List<Asset> = this.assetList.getChildren()

    public fun createIterator(
            assetIteratorType: AssetIteratorType = AssetIteratorType.HIERARCHY
    ): AssetIterator = this.assetList.createIterator(assetIteratorType)

    public fun accept(visitor: AbstractVisitor) {
        this.assetList.accept(visitor)
    }

    public fun cloneAssetList(): AssetList = this.assetList.clone()
}
