import org.exotechasset.exotechasset.entity.ConcreteAsset
import org.exotechasset.exotechasset.entity.AssetStatus
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.usecase.AssetList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

internal class AssetListTest {
    @Test
    fun testAssetList() {
        val asset1 = ConcreteAsset("Asset 1")
        val asset2 = ConcreteAsset("Asset 2")
        val asset3 = ConcreteAsset("Asset 3")

        val assetList = AssetList()

        assertEquals(0, assetList.size())

        assetList.addNewAsset(asset1)
        assetList.addNewAsset(asset2)
        assetList.addNewAsset(asset3)

        assertEquals(3, assetList.size())

        assertEquals(asset1, assetList.getAsset("Asset 1"))
        assertEquals(asset2, assetList.getAsset("Asset 2"))
        assertEquals(asset3, assetList.getAsset("Asset 3"))
    }

    @Test
    fun testAddNewAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetList = AssetList()
        assetList.addNewAsset(asset1)

        assertEquals(asset1, assetList.getAsset("asset1"))
    }

    @Test
    fun testModifyAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetList = AssetList(listOf(asset1))
        val modifiedAsset = ConcreteAsset(id = "asset1", status = AssetStatus.UNDEPLOYABLE)
        val result = assetList.modifyAsset(modifiedAsset)

        assertTrue(result)
        assertEquals(AssetStatus.UNDEPLOYABLE, assetList.getAsset("asset1")?.getStatus())
    }

    @Test
    fun testDeleteAssetById() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetList = AssetList(listOf(asset1))
        assetList.deleteAsset("asset1")

        assertFalse(assetList.getAsset("asset1") != null)
    }

    @Test
    fun testDeleteAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetList = AssetList(listOf(asset1))
        assetList.deleteAsset(asset1)

        assertFalse(assetList.getAsset("asset1") != null)
    }

    @Test
    fun testAuditAsset() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetList = AssetList(listOf(asset1))
        val now = Date.ofNow()

        assetList.auditAsset("asset1", now)

        assertEquals(now, assetList.getAsset("asset1")?.getAuditDate())
    }

    @Test
    fun testGetChildren() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetList = AssetList()
        assetList.addNewAsset(asset1)

        assertEquals(listOf(asset1), assetList.getChildren())
    }

    @Test
    fun testClone() {
        val asset1 = ConcreteAsset(id = "asset1")
        val assetList = AssetList()
        assetList.addNewAsset(asset1)

        val clonedAssetList = assetList.clone()
        clonedAssetList.deleteAsset(id = "asset1")

        assertNotEquals(listOf(asset1), clonedAssetList.getChildren())
        assertEquals(listOf(asset1), assetList.getChildren())
    }

}
