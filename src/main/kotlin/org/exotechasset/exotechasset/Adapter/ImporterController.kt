package org.exotechasset.exotechasset.Adapter

import org.exotechasset.exotechasset.adapter.ServiceController
import org.exotechasset.exotechasset.useCase.AssetListFile
import org.exotechasset.exotechasset.useCase.ExporterImporterHandler
import org.exotechasset.exotechasset.usecase.AssetList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class ImporterController {
    private val assetList: AssetList = ServiceController.assetList

    // Existing methods...

    @PostMapping("/import-assets")
    fun importAssets(@RequestBody request: Map<String, String>): ResponseEntity<Map<String, Any>> {
        return try {
            val fileContent = request["fileContent"] ?: throw IllegalArgumentException("File content is required")

            val importerHandler = ExporterImporterHandler()
            val assetListFile = AssetListFile()
            assetListFile.write(fileContent)
            importerHandler.importFile(assetList, assetListFile)

            ResponseEntity.ok(mapOf("success" to true))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("success" to false))
        }
    }
}