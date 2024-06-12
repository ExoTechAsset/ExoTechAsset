package org.exotechasset.exotechasset.Entity

import org.exotechasset.exotechasset.entity.*
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CsvVisitorTest {

    @Test
    fun getCsv() {
        var csvVisitor:CsvVisitor = CsvVisitor();
        val asset1 = Asset(id = "Asset 1", status = AssetStatus.UNDEPLOYABLE, assignee = "Kuo", auditDate = Date(1714608000), location = Location("Room 1623"))
        val asset2 = Asset("Asset 2")
        val assetList = AssetList()
        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val expect = "id, status, assignee, auditDate, location, changelog\n" +
                "Asset 1, Undeployable, Kuo, 2024-05-02T00:00:00Z, Room 1623, []\n" +
                "Asset 2, Deployable, null, null, , []\n"
        assetList.accept(csvVisitor);

        kotlin.test.assertEquals(expect, csvVisitor.get());

    }
}