package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.*
import org.exotechasset.exotechasset.usecase.ReportFactory
import org.exotechasset.exotechasset.usecase.ReportHandler
import org.exotechasset.exotechasset.usecase.ReportType
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ReportFactoryTest {
    val reportFactory = ReportFactory()
    @Test
    fun generateTable() {

        val assetList = AssetList()
        var mutablemap  = mutableMapOf<AssetGetBy, Any>(AssetGetBy.ID to 1, AssetGetBy.STATUS to 2)
        var metric: Metric = Metric(mutablemap)
        var result:Any
        var expects:Any
        val asset1 = ConcreteAsset("Asset 1")
        val asset2 = ConcreteAsset("Asset 2")

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val report = reportFactory.generate(ReportType.TABLE, metric)
        assetList.accept(report)
        result = report.get()
        expects = "[{\"id\":\"Asset 1\",\"status\":\"Deployable\"}" +
                ",{\"id\":\"Asset 2\",\"status\":\"Deployable\"}]"


        kotlin.test.assertEquals(expects, result.toString());
    }

    @Test
    fun generateBarChartTest() {
        val assetList = AssetList()
        var mutablemap  = mutableMapOf<AssetGetBy, Any>(AssetGetBy.ID to "x", AssetGetBy.STATUS to "y")
        var metric:Metric = Metric(mutablemap)
        var result:Any
        var expects:Any
        val asset1 = ConcreteAsset("Asset 1")
        val asset2 = ConcreteAsset("Asset 2")

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        val report = reportFactory.generate(ReportType.BAR, metric)
        assetList.accept(report)
        result = report.get()
        expects = "{\"x\":[\"Asset 1\",\"Asset 2\"],\"y\":[\"Deployable\",\"Deployable\"]}"


        kotlin.test.assertEquals(expects, result.toString());
    }
    @Test
    fun generatePieChartTest() {
        val assetList = AssetList()
        val metric = Metric(mutableMapOf<AssetGetBy, Any>());
        metric.addMetrics(AssetGetBy.STATUS, "1");
        var result:Any
        var expects:Any
        val asset1 = ConcreteAsset("Asset 1", status = AssetStatus.UNDEPLOYABLE)
        val asset2 = ConcreteAsset("Asset 2")
        val asset3 = ConcreteAsset("Asset 3")

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        assetList.addNewAsset(asset3)
        val report = reportFactory.generate(ReportType.PIE, metric)
        assetList.accept(report)
        result = report.get()
        expects = "{\"Deployable\":2,\"Undeployable\":1}"


        kotlin.test.assertEquals(expects, result.toString());
    }
}
