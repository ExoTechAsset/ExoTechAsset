package org.exotechasset.exotechasset.entity

abstract class Filter {
    companion object Factory {
        var currentMaxId: Int = 0
    }

    public val id: Int

    constructor() {
        this.id = currentMaxId
        currentMaxId += 1
    }

    open public fun meet(assetList: List<Asset>): List<Asset> = assetList
}
