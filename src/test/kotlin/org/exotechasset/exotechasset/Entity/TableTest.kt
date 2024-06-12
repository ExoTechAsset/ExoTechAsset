package org.exotechasset.exotechasset.Entity

import org.exotechasset.exotechasset.entity.*
import org.exotechasset.exotechasset.usecase.AssetList
import kotlin.test.Test
import kotlin.test.assertEquals

import org.junit.jupiter.api.Assertions.*

class TableTest {

    @Test
    fun getTable() {
        val metric = Metric(mutableMapOf<AssetGetBy, Any>());
        metric.addMetrics(AssetGetBy.ID, "1");
        metric.addMetrics(AssetGetBy.STATUS, "2");
        var table: Table = Table(metric);
        val asset1 = Asset("Asset 1", status = AssetStatus.UNDEPLOYABLE)
        val asset2 = Asset("Asset 2")
        val assetList = AssetList()
        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val expect_asset1 = "{\"id\":\"Asset 1\",\"status\":\"Undeployable\"}"
        val expect_asset2 = "{\"id\":\"Asset 2\",\"status\":\"Deployable\"}"
        assetList.accept(table);

        assertEquals(expect_asset1, table.get()[0].toString());
        assertEquals(expect_asset2, table.get()[1].toString());
    }


}