package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.usecase.AssetIterator
import org.exotechasset.exotechasset.usecase.AssetHandler
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
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
class AssetManagementController {
    private val assetHandler: AssetHandler = ServiceController.assetHandler

    @GetMapping("/assets")
    public fun getAssetIdList(): String {
        val response: JSONObject = JSONObject()
        val iterator: AssetIterator = this.assetHandler.createIterator()
        while (iterator.hasNext()) {
            iterator.next()
            val asset: Asset = iterator.getValue()!!

            val assetJsonObject: JSONObject = AssetDto(asset).toJSONObject()
            response.put(asset.getId(), assetJsonObject)
        }
        return response.toString()
    }
    @GetMapping("/assets/{id}")
    public fun getAsset(@PathVariable(value = "id") id: String): String {
        val asset:Asset? = this.assetHandler.getAsset(id)
        if (asset == null) {
            // TODO: error handling
            return "{\n}"
        }

        val response: JSONObject = JSONObject()
        response.put(id, AssetDto(asset).toJSONObject())

        return response.toString()
    }

    @PostMapping("/assets")
    fun addAsset(@RequestBody assetDto: AssetDto, @RequestBody parentId: String? = null) {
        val asset = assetDto.toAsset()
        val result:Boolean = this.assetHandler.addNewAsset(asset, parentId)
        if (result == false) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add asset")
        }
    }

    @DeleteMapping("/assets/{id}")
    fun deleteAsset(@PathVariable(value = "id") id: String) {
        this.assetHandler.deleteAsset(id)
    }

    @PutMapping("/assets/{id}")
    fun modifyAsset(@PathVariable(value = "id") id: String, @RequestBody assetDto: AssetDto) {
        val updatedAsset = assetDto.toAsset()
        try {
            this.assetHandler.modifyAsset(updatedAsset)
        }
        catch(exception: IllegalArgumentException) {
            // TODO
        }
    }

    @PutMapping("/assets/{id}/audit")
    fun auditAsset(@PathVariable(value = "id") id: String) {
        this.assetHandler.auditAsset(id)
    }
}
