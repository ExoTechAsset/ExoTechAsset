package org.exotechasset.exotechasset.UseCase


import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.AssetGetBy
import org.exotechasset.exotechasset.entity.AssetStatus
import org.exotechasset.exotechasset.entity.Metric
import org.exotechasset.exotechasset.useCase.ReportHandler
import org.exotechasset.exotechasset.useCase.ReportType
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ReportHandlerTest {

    @Test
    fun generateTableTest() {
        val assetList = AssetList()
        var mutablemap  = mutableMapOf<AssetGetBy, Any>(AssetGetBy.ID to 1, AssetGetBy.STATUS to 2)
        var metric: Metric = Metric(mutablemap)
        var result:Any
        var expects:Any
        val asset1 = Asset("Asset 1")
        val asset2 = Asset("Asset 2")

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val report = ReportHandler(assetList).generateReport(ReportType.TABLE, metric)
        result = report.get()
        expects = "[{\"id\":\"Asset 1\",\"status\":\"Deployable\"}" +
                ",{\"id\":\"Asset 2\",\"status\":\"Deployable\"}]"


        kotlin.test.assertEquals(expects, result.toString());

    }
    @Test
    fun generateBarChartTest() {
        val assetList = AssetList()
        var mutablemap  = mutableMapOf<AssetGetBy, Any>(AssetGetBy.ID to "y", AssetGetBy.STATUS to "x")
        var metric:Metric = Metric(mutablemap)
        var result:Any
        var expects:Any
        val asset1 = Asset("Asset 1")
        val asset2 = Asset("Asset 2")

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val report = ReportHandler(assetList).generateReport(ReportType.BAR, metric)
        result = report.get()
        expects =
                "{\"x\":[\"Deployable\",\"Deployable\"],\"y\":[\"Asset 1\",\"Asset 2\"]}"


        kotlin.test.assertEquals(expects, result.toString());
    }
    @Test
    fun generatePieChartTest() {
        val assetList = AssetList()
        val metric = Metric(mutableMapOf<AssetGetBy, Any>());
        metric.addMetrics(AssetGetBy.STATUS, "1");
        var result:Any
        var expects:Any
        val asset1 = Asset("Asset 1", status = AssetStatus.UNDEPLOYABLE)
        val asset2 = Asset("Asset 2")
        val asset3 = Asset("Asset 3")

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        assetList.addNewAsset(asset3)
        val report = ReportHandler(assetList).generateReport(ReportType.PIE, metric)
        result = report.get()
        expects = "{\"Deployable\":2,\"Undeployable\":1}"


        kotlin.test.assertEquals(expects, result.toString());
    }

    @Test
    fun generateCsvTest(){
        val assetList = AssetList()
        var mutablemap  = mutableMapOf<AssetGetBy, Any>(AssetGetBy.ID to 1, AssetGetBy.STATUS to 2)
        var metric: Metric = Metric(mutablemap)
        var result:Any
        var expects:Any
        val asset1 = Asset("Asset 1")
        val asset2 = Asset("Asset 2")

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val report = ReportHandler(assetList).generateReport(ReportType.CSV, metric)
        result = report.get()
        expects = "id, status, assignee, auditDate, location, changelog\n" +
                "Asset 1, Deployable, null, null, , []\n" +
                "Asset 2, Deployable, null, null, , []\n"

        kotlin.test.assertEquals(expects, result.toString());
    }
}