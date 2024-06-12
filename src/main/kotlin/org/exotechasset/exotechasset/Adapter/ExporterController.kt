package org.exotechasset.exotechasset.Adapter

import ExportRequest
import org.exotechasset.exotechasset.adapter.ServiceController
import org.exotechasset.exotechasset.useCase.ExporterImporterHandler
import org.exotechasset.exotechasset.usecase.AssetList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ExporterController {
    private val assetList: AssetList = ServiceController.assetList
    @PostMapping("/export-assets")
    fun exportAssets(@RequestBody exportRequest: ExportRequest): ResponseEntity<String> {
        val path = exportRequest.getFilePath()
        if (!path.endsWith(".csv")) {
            return ResponseEntity.badRequest().body("File name must end with .csv. Provided path: $path")
        }

        return try {
            ExporterImporterHandler().exportFile(path, assetList)
            ResponseEntity.ok("Assets exported successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error exporting assets: ${e.message}. Provided path: $path")
        }
    }
}