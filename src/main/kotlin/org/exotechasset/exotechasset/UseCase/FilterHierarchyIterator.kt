package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Filter

open class FilterHierarchyIterator : FilterIterator {

    private val stack: ArrayDeque<FilterIterator> = ArrayDeque()

    public constructor(filter: FilterCRDFilterParameter) : super(filter) {}

    public constructor(filter: FilterCRDFilter) : super(filter) {
        this.stack.addLast(filter.createIterator())
    }

    public constructor(filter: Filter) : super(filter) {
        if (filter is FilterCRDFilter) {
            this.stack.addLast(filter.createIterator())
        }
    }

    override public fun next() {
        while (this.stack.isNotEmpty()) {
            val iterator: FilterIterator = this.stack.last()
            if (iterator.hasNext()) {
                iterator.next()
                this.filter = iterator.getValue()
                if (this.filter is FilterCRDFilter) {
                    val compositeFilter = this.filter as FilterCRDFilter
                    val filterIterator: FilterIterator = compositeFilter.createIterator()
                    this.stack.addLast(filterIterator)
                }
                return
            } else {
                this.stack.removeLast()
            }
        }
    }

    override public fun hasNext(): Boolean {
        for (iterator in this.stack) {
            if (iterator.hasNext() == true) {
                return true
            }
        }
        return false
    }

    public fun getParent():FilterCRDFilter? {
        if (this.stack.size > 1) {
            return this.stack[this.stack.size - 2].getValue() as FilterCRDFilter
        } else {
            return null
        }
    }
}
