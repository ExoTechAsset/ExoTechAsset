package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.adapter.ServiceController
import org.exotechasset.exotechasset.usecase.AssetListFile
import org.exotechasset.exotechasset.usecase.ExporterImporterHandler
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
    private val exporterImporterHandler: ExporterImporterHandler = ServiceController.exporterImporterHandler

    // Existing methods...

    @PostMapping("/import-assets")
    fun importAssets(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        return try {
            val fileContent = String(file.bytes)

            val webFile:WebFileTransmission = WebFileTransmission()
            webFile.giveReadString(fileContent)

            this.exporterImporterHandler.importFile(webFile)

            ResponseEntity("success", HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("success" to false))
        }
    }
}