package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.usecase.AssetIterator
import org.exotechasset.exotechasset.usecase.AssetList
import org.exotechasset.exotechasset.usecase.FilterChain
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FilterController {
    private val assetList: AssetList = ServiceController.assetList
    private val filterChain: FilterChain = ServiceController.filterChain

    @GetMapping("/filters")
    public fun getFilterList(): String {
        // TODO
        // val response: JSONObject = JSONObject()
        // val iterator: AssetIterator = this.assetList.createIterator()
        // while (iterator.hasNext()) {
        //     iterator.next()
        //     val asset: Asset = iterator.getValue()!!

        //     val assetJsonObject: JSONObject = AssetDto(asset).toJSONObject()
        //     response.put(asset.getId(), assetJsonObject)
        // }
        // return response.toString()
        // TODO: size()
        return ""
    }

    // TODO
    // @PostMapping("/filters")
    // fun addFilter(@RequestBody filterDto: FilterDto) {
    //     // TODO: FilterDto
    //     // TODO
    //     // val filter:Filter = filterDto.toFilter()
    //     // this.filterList.addFilter(filter)
    // }

    @DeleteMapping("/filters/{id}")
    fun deleteFilter(@PathVariable(value = "id") id: String) {
        // TODO
    }

    @GetMapping("/filters/assets")
    public fun getFilterAsset(): String {
        val response: JSONObject = JSONObject()
        val iterator: AssetIterator = this.filterChain.filterAsset()
.createIterator()
        while (iterator.hasNext()) {
            iterator.next()
            val asset: Asset = iterator.getValue()!!

            val assetJsonObject: JSONObject = AssetDto(asset).toJSONObject()
            response.put(asset.getId(), assetJsonObject)
        }
        return response.toString()
    }
}
