package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter

class FilterChain(assetList: AssetList) {
    private val assetList: AssetList = assetList
    private var filterList: MutableSet<Filter> = mutableSetOf()

    public fun addFilter(filterType: FilterType, parentIndex: Int? = null): Filter {
        val filter: Filter = FilterFactory().create(filterType)
        this.addFilter(filter, parentIndex)
        return filter
    }

    public fun addFilter(filter: Filter, parentIndex: Int? = null): Filter {
        if (parentIndex == null) {
            this.filterList.add(filter)
        } else {
            val parentFilter:Filter? = this.getFilter(parentIndex)
            if (parentFilter is FilterCRDFilter) {
                parentFilter.addFilter(filter)
            }
        }
        return filter
    }

    public fun modifyFilter(filter: Filter): Boolean {
        val targetFilter:Filter? = this.getFilter(filter.id)
        if (targetFilter == null) {
            return false
        }
        return true
    }

    public fun getFilter(index: Int): Filter? {
        for (rootFilter in this.filterList) {
            val filterIterator: FilterIterator = FilterHierarchyIterator(rootFilter)
            while (filterIterator.hasNext()) {
                filterIterator.next()
                val filter:Filter = filterIterator.getValue()
                if (filter.id == index) {
                    return filter
                }
            }
        }
        return null
    }

    public fun removeFilter(index: Int) {
        for (rootFilter in this.filterList) {
            if (rootFilter.id == index) {
                this.filterList.remove(rootFilter)
                return
            }
            val filterIterator: FilterHierarchyIterator = FilterHierarchyIterator(rootFilter)
            while (filterIterator.hasNext()) {
                filterIterator.next()
                val filter:Filter = filterIterator.getValue()
                if (filter.id != index) {
                    continue
                }
                // filter.id == index
                val parent:FilterCRDFilter? = filterIterator.getParent()
                if (parent == null) {
                    this.filterList.remove(rootFilter)
                } else {
                    parent.removeFilter(index)
                }
                return
            }
        }
    }

    public fun filterAsset(): AssetList {
        var filteredAssetList = assetList.getChildren()
        for (filter in this.filterList) {
            filteredAssetList = filter.meet(filteredAssetList)
        }
        return AssetList(filteredAssetList)
    }

    public fun getFilterList(): List<Filter> = this.filterList.toList()

    public fun size() = this.filterList.size
}
