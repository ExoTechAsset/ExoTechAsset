package org.exotechasset.exotechasset.Entity

import org.exotechasset.exotechasset.entity.*
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

import org.junit.jupiter.api.Assertions.*
import org.json.JSONObject
class BarChartTest {
//    @Test
//    fun getBarDatas() {
//        val metric = Metric(mutableMapOf<AssetGetBy, Any>());
//        metric.addMetrics(AssetGetBy.ID, "1");
//        metric.addMetrics(AssetGetBy.STATUS, "2");
//        var barChart: BarChart = BarChart(metric);
//        val asset: Asset = Asset("AS-01");
//        val expect_id = "AS-01"
//        val expect_Status = "Deployable"
//
//        asset.accept(barChart);
//
//        assertEquals(expect_id, barChart.get().getValue(AssetGetBy.ID)[0]);
//        assertEquals(expect_Status, barChart.get().getValue(AssetGetBy.STATUS)[0]);
//    }

    @Test
    fun getBarDatas() {
        val metric = Metric(mutableMapOf<AssetGetBy, Any>());
        metric.addMetrics(AssetGetBy.ID, "y");
        metric.addMetrics(AssetGetBy.STATUS, "x");
        var barChart: BarChart = BarChart(metric);
        val asset1 = Asset("Asset 1", status = AssetStatus.UNDEPLOYABLE)
        val asset2 = Asset("Asset 2")
        val asset3 = Asset("Asset 3")
        val assetList = AssetList()
        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        assetList.addNewAsset(asset3)
        val expect = "{\"x\":[\"Undeployable\",\"Deployable\"," +
                "\"Deployable\"],\"y\":[\"Asset 1\",\"Asset 2\",\"Asset 3\"]}"

        assetList.accept(barChart)

        assertEquals(expect, barChart.get().toString());
    }
}

