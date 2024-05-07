package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.entity.ToStringInterface
import org.exotechasset.exotechasset.usecase.FilterCRDFilter
import org.exotechasset.exotechasset.usecase.FilterCRDFilterParameter
import org.exotechasset.exotechasset.usecase.FilterChain
import org.exotechasset.exotechasset.usecase.FilterFactory
import org.exotechasset.exotechasset.usecase.FilterIterator
import org.exotechasset.exotechasset.usecase.FilterIteratorFactory
import org.exotechasset.exotechasset.usecase.FilterIteratorType
import org.exotechasset.exotechasset.usecase.FilterParameter
import org.exotechasset.exotechasset.usecase.FilterParameterBy
import org.exotechasset.exotechasset.usecase.FilterType
import org.json.JSONArray
import org.json.JSONObject

data public class FilterDto(
        val id: Int?,
        val type: String,
        val inputs: String = "",
) : ToStringInterface {
    private val filterChain: FilterChain = ServiceController.filterChain

    constructor(
            filter: Filter
    ) : this(
            filter.id,
            FilterType.of(filter).toString(),
            when (filter) {
                is FilterCRDFilter -> {
                    val array: JSONArray = JSONArray()
                    val iterator: FilterIterator =
                            FilterIteratorFactory(filter)
                                    .create(FilterIteratorType.SIMPLE_FILTER_ITERATOR)
                    while (iterator.hasNext()) {
                        iterator.next()
                        val iteratorFilter: Filter = iterator.getValue()
                        val jsonObject: JSONObject = FilterDto(iteratorFilter).toJSONObject()
                        array.put(jsonObject)
                    }
                    array.toString()
                }
                is FilterCRDFilterParameter -> {
                    val array: JSONArray = JSONArray()
                    val iterator: FilterIterator =
                            FilterIteratorFactory(filter)
                                    .create(FilterIteratorType.SIMPLE_FILTER_ITERATOR)
                    while (iterator.hasNext()) {
                        iterator.next()
                        val iteratorFilter: Filter = iterator.getValue()
                        val filterParameterList: List<FilterParameter> =
                                (iteratorFilter as FilterCRDFilterParameter).getFilterParameters()
                        for (filterParameter: FilterParameter in filterParameterList) {
                            val jsonObject: JSONObject = JSONObject()
                            jsonObject.put("type", "parameter")
                            jsonObject.put("parameter", filterParameter.by.toString())
                            jsonObject.put(
                                    "value",
                                    if (filterParameter.by == FilterParameterBy.VALUE)
                                            filterParameter.value
                                    else null
                            )
                            array.put(jsonObject)
                        }
                    }
                    array.toString()
                }
                else -> ""
            }
    ) {}

    public fun toJSONObject(): JSONObject =
            JSONObject(mapOf("id" to this.id, "type" to this.type, "inputs" to this.inputs))

    public fun toFilter(): Filter {
        val filter: Filter = FilterFactory().create(FilterType.valueOf(this.type), this.id)
        if (this.inputs.length == 0) {
            return filter
        }

        JSONArray(this.inputs).forEach {
            val jsonObject: JSONObject = it as JSONObject
            when (jsonObject.getString("type")) {
                "parameter" -> {
                    val filterParameter: FilterParameter =
                            FilterParameter(
                                    FilterParameterBy.valueOf(jsonObject.getString("parameter")),
                                    jsonObject.getString("value")
                            )
                    (filter as FilterCRDFilterParameter).addFilterParameter(filterParameter)
                }
                else -> {
                    val subId = jsonObject.getInt("id")
                    val subType = jsonObject.getString("type")
                    val subInputs = jsonObject.getString("inputs")
                    (filter as FilterCRDFilter).addFilter(
                            FilterDto(subId, subType, subInputs).toFilter()
                    )
                }
            }
        }
        return filter
    }

    public override fun toString(): String = this.toJSONObject().toString()
}
