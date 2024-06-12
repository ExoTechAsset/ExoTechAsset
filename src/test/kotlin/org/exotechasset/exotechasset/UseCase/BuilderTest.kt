package org.exotechasset.exotechasset.UseCase

import org.exotechasset.exotechasset.entity.Asset
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BuilderTest {
    lateinit var builder: Builder
    @BeforeEach
    fun setUp() {
        builder = Builder()
        builder.createNewAsset()
    }

    @Test
    fun buildAssetId() {
        builder.buildAssetId("asset01")
        assertEquals("asset01", builder.get().getId())
    }

    @Test
    fun buildAssetStatus() {
        builder.buildAssetStatus("Deployable")
        assertEquals("Deployable", builder.get().getStatus().toString())
    }

    @Test
    fun buildAssetLocation() {
        builder.buildAssetLocation("location01")
        assertEquals("location01", builder.get().getLocation()?.get())
    }

    @Test
    fun buildAssetAssignee() {
        builder.buildAssetAssignee("Kuo")
        assertEquals("Kuo", builder.get().getAssignee())
    }

    @Test
    fun buildAssetAuditDate() {
        builder.buildAssetAuditDate("2021-09-01T00:00:00Z")
        assertEquals("2021-09-01T00:00:00Z", builder.get().getAuditDate().toString())
    }
}