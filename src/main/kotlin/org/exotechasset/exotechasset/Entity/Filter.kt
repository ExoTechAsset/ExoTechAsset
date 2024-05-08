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

    constructor(id: Int) {
        this.id = id
    }

    constructor(id: Int? = null) {
        if (id == null) {
            this.id = currentMaxId
            currentMaxId += 1
        } else {
            this.id = id
        }
    }
    
    public open fun modify(filter: Filter) {
        require(this.id == filter.id)
    }

    open public fun meet(assetList: List<Asset>): List<Asset> = assetList
}
