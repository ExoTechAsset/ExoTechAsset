package org.exotechasset.exotechasset.UseCase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.AssetGetBy
import org.exotechasset.exotechasset.usecase.AssetListFile
import org.exotechasset.exotechasset.usecase.ExporterImporterHandler
import org.exotechasset.exotechasset.usecase.AssetHandler
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class ExporterImporterHandlerTest {
    private lateinit var assetHandler: AssetHandler
    private lateinit var exporterImporterHandler: ExporterImporterHandler

    @BeforeEach
    fun setUp() {
        this.assetHandler = AssetHandler()
        var asset1:Asset = Asset("asset1")
        var asset2:Asset = Asset("asset2")
        this.assetHandler.addNewAsset(asset1)
        this.assetHandler.addNewAsset(asset2)
        this.exporterImporterHandler = ExporterImporterHandler(this.assetHandler)
    }
    // TODO Change AssetList to AssetHandler
    @Test
    fun exportFileTest() {
        val assetListFile = exporterImporterHandler.exportFile("./test.csv")
        val expect = "id, status, assignee, auditDate, location, changelog\n" +
                "asset1, Deployable, null, null, , []\n" +
                "asset2, Deployable, null, null, , []\n"
        assetListFile.readCsv()
        assertEquals(expect, assetListFile.getContent())
    }
//
    @Test
    fun import() {
        val path = "./test.csv"
        val assetListFile = exporterImporterHandler.exportFile(path)
        val emptyAssetHandler = AssetHandler()
        val emptyExporterImporterHandler = ExporterImporterHandler(emptyAssetHandler)
        val result = emptyExporterImporterHandler.importFile(assetListFile)

        assertEquals(2, result.size())
        val asset1 = result.getAsset("asset1")

        assertEquals("asset1", asset1!!.getId())
        assertEquals("Deployable", asset1.getStatus().toString())
        assertEquals("null", asset1.getAssignee().toString())
        assertEquals("null", asset1.getAuditDate().toString())
        assertEquals("", asset1.getLocation()?.get())

        val asset2 = result.getAsset("asset2")
        assertEquals("asset2", asset2!!.getId())
        assertEquals("Deployable", asset2.getStatus().toString())
        assertEquals("null", asset2.getAssignee().toString())
        assertEquals("null", asset2.getAuditDate().toString())
        assertEquals("", asset2.getLocation()?.get())


    }
}