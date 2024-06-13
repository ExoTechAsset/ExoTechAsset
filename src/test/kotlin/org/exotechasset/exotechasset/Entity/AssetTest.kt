import org.exotechasset.exotechasset.entity.*
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AssetTest {
    private val asset: Asset = ConcreteAsset("AS-01")

    @Test
    fun suite() {
        assertEquals("AS-01", this.asset.getId())
        assertEquals(AssetStatus.DEPLOYABLE, this.asset.getStatus())
        assertEquals(null, this.asset.getAssignee())
        assertEquals(null, this.asset.getAuditDate())
        assertEquals(null, this.asset.getLocation())
        assertEquals(emptyList(), this.asset.getChildrenIdList())
    }

    @Test
    fun testSetStatus() {
        val newStatus = AssetStatus.PENDING
        this.asset.setStatus(newStatus)
        assertEquals(newStatus, this.asset.getStatus())
    }

    @Test
    fun testSetAssignee() {
        val newAssignee = "Chris Chen"
        this.asset.setAssignee(newAssignee)
        assertEquals(newAssignee, this.asset.getAssignee())
    }

    @Test
    fun testSetAuditDate() {
        val newAuditDate = Date(1714608000)
        this.asset.setAuditDate(newAuditDate)
        assertEquals(newAuditDate, this.asset.getAuditDate())
    }

    @Test
    fun testSetLocation() {
        val newLocation = Location("Room 1623")
        this.asset.setLocation(newLocation)
        assertEquals(newLocation, this.asset.getLocation())
    }

    @Test
    fun testGetDescription() {
        assertEquals("", asset.getDescription())
    }

    @Test
    fun testModify() {
        val asset2 = ConcreteAsset(asset.getId(), status = AssetStatus.UNDEPLOYABLE)
        assertEquals(asset.getId(), asset2.getId())
        asset.modify(asset2)
        assertEquals(AssetStatus.UNDEPLOYABLE, this.asset.getStatus())
    }

    @Test
    fun testAudit() {
        val now = Date.ofNow()

        asset.audit(now);

        assertEquals(now, this.asset.getAuditDate())
    }
}
