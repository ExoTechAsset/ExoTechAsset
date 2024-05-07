package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.entity.ToStringInterface
import org.exotechasset.exotechasset.usecase.FilterChain
import org.exotechasset.exotechasset.usecase.FilterFactory
import org.exotechasset.exotechasset.usecase.FilterType
import org.json.JSONObject

data public class FilterDto(): ToStringInterface {
    private val filterChain: FilterChain = ServiceController.filterChain

    constructor(filter:Filter) : this() {
        filterChain.addFilter(filter)
    }

    constructor(filter:Filter, parentIndex: Int) : this() {
        filterChain.addFilter(filter, parentIndex)
    }

    constructor(filterType: FilterType) : this() {
        filterChain.addFilter(filterType)
    }

    constructor(filterType: FilterType, parentIndex: Int) : this() {
        filterChain.addFilter(filterType, parentIndex)
    }

    public fun toJSONObject(): JSONObject {
        val json = JSONObject()
        filterChain.getFilterList()
        // TODO
        // json.put("id", this.id)
        return json
    }

    // TODO: use builder pattern to rewrite the logic.
    // public fun toFilter(): Filter {
    //     return Filter(
    //         this.filterType)
    // }

    public override fun toString(): String = this.toJSONObject().toString()
}
