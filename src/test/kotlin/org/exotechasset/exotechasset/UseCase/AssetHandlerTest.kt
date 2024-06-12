import org.exotechasset.exotechasset.entity.ConcreteAsset
import org.exotechasset.exotechasset.entity.AssetStatus
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.usecase.AssetHandler
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

internal class AssetHandlerTest {
    @Test
    fun testAssetHandler() {
        val asset1 = ConcreteAsset("Asset 1")
        val asset2 = ConcreteAsset("Asset 2")
        val asset3 = ConcreteAsset("Asset 3")

        val assetHandler = AssetHandler()

        assertEquals(0, assetHandler.size())

        assetHandler.addNewAsset(asset1)
        assetHandler.addNewAsset(asset2)
        assetHandler.addNewAsset(asset3)

        assertEquals(3, assetHandler.size())

        assertEquals(asset1, assetHandler.getAsset("Asset 1"))
        assertEquals(asset2, assetHandler.getAsset("Asset 2"))
        assertEquals(asset3, assetHandler.getAsset("Asset 3"))
    }

    @Test
    fun testAddNewAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetHandler = AssetHandler()
        assetHandler.addNewAsset(asset1)

        assertEquals(asset1, assetHandler.getAsset("asset1"))
    }

    @Test
    fun testModifyAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetHandler = AssetHandler(listOf(asset1))
        val modifiedAsset = ConcreteAsset(id = "asset1", status = AssetStatus.UNDEPLOYABLE)
        val result = assetHandler.modifyAsset(modifiedAsset)

        assertTrue(result)
        assertEquals(AssetStatus.UNDEPLOYABLE, assetHandler.getAsset("asset1")?.getStatus())
    }

    @Test
    fun testDeleteAssetById() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetHandler = AssetHandler(listOf(asset1))
        assetHandler.deleteAsset("asset1")

        assertFalse(assetHandler.getAsset("asset1") != null)
    }

    @Test
    fun testDeleteAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetHandler = AssetHandler(listOf(asset1))
        assetHandler.deleteAsset(asset1)

        assertFalse(assetHandler.getAsset("asset1") != null)
    }

    @Test
    fun testAuditAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetHandler = AssetHandler(listOf(asset1))
        val now = Date.ofNow()

        assetHandler.auditAsset("asset1", now)

        assertEquals(now, assetHandler.getAsset("asset1")?.getAuditDate())
    }

    @Test
    fun testGetChildren() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetHandler = AssetHandler()
        assetHandler.addNewAsset(asset1)

        assertEquals(listOf(asset1), assetHandler.getChildren())
    }

    @Test
    fun testClone() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetHandler = AssetHandler()
        assetHandler.addNewAsset(asset1)

        val clonedAssetHandler = assetHandler.cloneAssetList()
        clonedAssetHandler.deleteAsset(id = "asset1")

        assertNotEquals(listOf(asset1), clonedAssetHandler.getChildren())
        assertEquals(listOf(asset1), assetHandler.getChildren())
    }

}
