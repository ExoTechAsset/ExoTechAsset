package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Filter
import org.exotechasset.exotechasset.usecase.AssetIterator
import org.exotechasset.exotechasset.usecase.AssetList
import org.exotechasset.exotechasset.usecase.FilterChain
import org.json.JSONObject
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FilterController {
    private val assetList: AssetList = ServiceController.assetList
    private val filterChain: FilterChain = ServiceController.filterChain

    @GetMapping("/filters")
    public fun getFilterList(): String {
        val json = JSONObject()
        for (filter in this.filterChain.getFilterList()) {
            json.put(filter.id.toString(), FilterDto(filter).toJSONObject())
        }
        return json.toString()
    }

    @PostMapping("/filters")
    fun addFilter(@RequestBody filterDto: FilterDto) {
        val filter:Filter = filterDto.toFilter()
        this.filterChain.addFilter(filter)
    }

    @DeleteMapping("/filters/{id}")
    fun deleteFilter(@PathVariable(value = "id") id: Int) {
        this.filterChain.removeFilter(id)
    }

    @GetMapping("/filters/assets")
    public fun getFilterAsset(): String {
        val response: JSONObject = JSONObject()
        val iterator: AssetIterator = this.filterChain.filterAsset().createIterator()
        while (iterator.hasNext()) {
            iterator.next()
            val asset: Asset = iterator.getValue()!!

            val assetJsonObject: JSONObject = AssetDto(asset).toJSONObject()
            response.put(asset.getId(), assetJsonObject)
        }
        return response.toString()
    }
}
