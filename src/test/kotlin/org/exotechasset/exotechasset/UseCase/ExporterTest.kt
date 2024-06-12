package org.exotechasset.exotechasset.UseCase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.ConcreteAsset
import org.exotechasset.exotechasset.usecase.Exporter
import org.exotechasset.exotechasset.usecase.AssetHandler
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ExporterTest {

    @Test
    fun exportTest() {
        val assetHandler = AssetHandler()
        val exporter = Exporter(assetHandler)

        val asset1:Asset = ConcreteAsset("asset1")
        val asset2:Asset = ConcreteAsset("asset2")
        assetHandler.addNewAsset(asset1)
        assetHandler.addNewAsset(asset2)

        val assetListFile = exporter.export("test.csv")
        val expect = "id, status, assignee, auditDate, location, changelog, parentId\n" +
                "asset1, Deployable, null, null, , [], \n" +
                "asset2, Deployable, null, null, , [], \n"
        assetListFile.readCsv()
        assertEquals(expect, assetListFile.getContent())
    }
}