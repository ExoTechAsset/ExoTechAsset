package org.exotechasset.exotechasset.usecase

interface FilterCRDFilterParameter {
    public fun addFilterParameter(filterParameterList: List<FilterParameter>) {
        for (filterParameter in filterParameterList) {
            this.addFilterParameter(filterParameter)
        }
    }

    public fun addFilterParameter(filterParameter: FilterParameter)
    public fun getFilterParameter(index: Int): FilterParameter
    public fun removeFilterParameter(index: Int)
    public fun createIterator(): FilterIterator = FilterIterator(this)
}
