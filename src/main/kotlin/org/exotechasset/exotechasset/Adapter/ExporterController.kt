package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.adapter.ServiceController
import org.exotechasset.exotechasset.usecase.ExporterImporterHandler
import org.exotechasset.exotechasset.usecase.AssetHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.core.io.Resource
import java.io.ByteArrayInputStream
import org.springframework.http.HttpHeaders
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType

@RestController
class ExporterController {
    private val exporterImporterHandler: ExporterImporterHandler = ServiceController.exporterImporterHandler

    @PostMapping("/export-assets")
    fun exportAssets(@RequestParam content: String = "Default Content"): ResponseEntity<InputStreamResource> {
        val file:WebFileTransmission = WebFileTransmission()
        this.exporterImporterHandler.exportFile(file)
        val text:String = file.getWriteResponse()
        val inputStream = ByteArrayInputStream(text.toByteArray())
        val resource:InputStreamResource = InputStreamResource(inputStream)

        // Set headers
        val headers = HttpHeaders().apply {
            add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=download.csv")
            // add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
            
        }

        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(text.length.toLong())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource)
    }
}