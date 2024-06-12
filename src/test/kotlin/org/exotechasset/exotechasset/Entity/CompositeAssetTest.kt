import org.exotechasset.exotechasset.entity.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CompositeAssetTest {
    @Test
    fun testConstructor() {
        val id = "123"
        val status = AssetStatus.DEPLOYABLE
        val assignee = "John Doe"
        val auditDate = Date(1714608000)
        val location = Location("Room 1623")

        val asset = CompositeAsset(id, status, assignee, auditDate, location)

        assertEquals(id, asset.getId())
        assertEquals(status, asset.getStatus())
        assertEquals(assignee, asset.getAssignee())
        assertEquals(auditDate, asset.getAuditDate())
        assertEquals(location, asset.getLocation())
    }

    @Test
    fun testGetChildrenIdList() {
        val compositeAsset = CompositeAsset(id = "parent")
        val child1 = ConcreteAsset(id = "child1")
        val child2 = ConcreteAsset(id = "child2")

        compositeAsset.addChild(child1)
        compositeAsset.addChild(child2)

        assertEquals(listOf("child1", "child2"), compositeAsset.getChildrenIdList())
    }

    @Test
    fun testGetChildren() {
        val compositeAsset = CompositeAsset(id = "parent")
        val child1 = ConcreteAsset(id = "child1")
        val child2 = ConcreteAsset(id = "child2")

        compositeAsset.addChild(child1)
        compositeAsset.addChild(child2)

        assertEquals(listOf(child1, child2), compositeAsset.getChildren())
    }

    @Test
    fun testHasChildren() {
        val compositeAsset = CompositeAsset(id = "parent")
        val child1 = ConcreteAsset(id = "child1")

        assertEquals(false, compositeAsset.hasChildren())

        compositeAsset.addChild(child1)

        assertEquals(true, compositeAsset.hasChildren())
    }

    @Test
    fun testAdd() {
        val compositeAsset = CompositeAsset(id = "parent")
        val child1 = ConcreteAsset(id = "child1")

        compositeAsset.addChild(child1)

        assertEquals(listOf("child1"), compositeAsset.getChildrenIdList())
    }

    @Test
    fun testRemoveById() {
        val compositeAsset = CompositeAsset(id = "parent")
        val child1 = ConcreteAsset(id = "child1")

        compositeAsset.addChild(child1)
        compositeAsset.removeChild("child1")

        assertEquals(false, compositeAsset.hasChildren())
    }

    @Test
    fun testRemoveByAsset() {
        val compositeAsset = CompositeAsset(id = "parent")
        val child1 = ConcreteAsset(id = "child1")

        compositeAsset.addChild(child1)
        compositeAsset.removeChild(child1)

        assertEquals(false, compositeAsset.hasChildren())
    }

    @Test
    fun testModify() {
        val compositeAsset = CompositeAsset(id = "parent")
        val child2 = ConcreteAsset(id = "parent", status = AssetStatus.UNDEPLOYABLE)

        compositeAsset.modify(child2)

        assertEquals(AssetStatus.UNDEPLOYABLE, compositeAsset.getStatus())
    }
}
