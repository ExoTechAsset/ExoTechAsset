package org.exotechasset.exotechasset.Entity

import org.exotechasset.exotechasset.entity.*
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertEquals

class PieChartTest {

    @Test
    fun getPieDatas() {
        val metric = Metric(mutableMapOf<AssetGetBy, Any>());
        metric.addMetrics(AssetGetBy.STATUS, "1");
        var pieChart:PieChart = PieChart(metric);
        val asset1 = Asset("Asset 1", status = AssetStatus.UNDEPLOYABLE)
        val asset2 = Asset("Asset 2")
        val asset3 = Asset("Asset 3")
        val assetList = AssetList()
        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        assetList.addNewAsset(asset3)
        val expect = "{\"Deployable\":2,\"Undeployable\":1}"

        assetList.accept(pieChart)

        assertEquals(expect, pieChart.get().toString());

    }
}