package org.exotechasset.exotechasset.UseCase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.useCase.Exporter
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ExporterTest {

    @Test
    fun exportTest() {
        val exporter = Exporter()
        val assetList = AssetList()
        val asset1 = Asset("asset1")
        val asset2 = Asset("asset2")
        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val assetListFile = exporter.export("test.csv", assetList)
        val expect = "id, status, assignee, auditDate, location, changelog\n" +
                "asset1, Deployable, null, null, , []\n" +
                "asset2, Deployable, null, null, , []\n"
        assetListFile.readCsv()
        assertEquals(expect, assetListFile.getContent())
    }
}