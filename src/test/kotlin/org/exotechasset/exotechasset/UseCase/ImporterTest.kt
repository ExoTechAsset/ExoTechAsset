package org.exotechasset.exotechasset.UseCase

import org.exotechasset.exotechasset.useCase.*
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ImporterTest {
    private lateinit var importer:Importer

    private lateinit var assetListFile: AssetListFile
    @BeforeEach
    fun setUp() {
        importer = Importer()
        assetListFile = AssetListFile("./test.csv")
        val content = "id, status, assignee, auditDate, location, changelog\n" +
                "asset1, Deployable, null, null, , []\n"
        assetListFile.write(content)
        assetListFile.readCsv()
    }

    @Test
    fun buildAssetTest(){
        val scanner = CsvScanner()
        scanner.get(assetListFile)
        val asset = importer.buildAsset(scanner)
        assertEquals("asset1", asset.getId())
        assertEquals("Deployable", asset.getStatus().toString())
        assertEquals("null", asset.getAssignee().toString())
        assertEquals("null", asset.getAuditDate().toString())
        assertEquals("", asset.getLocation()?.get())
    }

    @Test
    fun importTest(){
        var assetList = AssetList()
        assetList = importer.import(assetList, assetListFile)
        assertEquals(1, assetList.size())
        val asset = assetList.getAsset("asset1")
        assertEquals("asset1", asset!!.getId())
        assertEquals("Deployable", asset.getStatus().toString())
        assertEquals("null", asset.getAssignee().toString())
        assertEquals("null", asset.getAuditDate().toString())
        assertEquals("", asset.getLocation()?.get())
    }

}