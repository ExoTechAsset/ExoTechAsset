import org.exotechasset.exotechasset.entity.ConcreteAsset
import org.exotechasset.exotechasset.entity.AssetStatus
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.entity.FilterField
import org.exotechasset.exotechasset.entity.Location
import org.exotechasset.exotechasset.usecase.FilterValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FilterValueTest {
    private val asset = ConcreteAsset("AS-01")

    @Test
    fun testGet() {
        assertEquals("AS-01", FilterValue(FilterField.ID).get(asset))
    }

    @Test
    fun testGetWithValue() {
        val asset =
                ConcreteAsset(
                        id = "asset1",
                        status = AssetStatus.UNDEPLOYABLE,
                        assignee = "John Doe",
                        auditDate = Date.ofNow(),
                        location = Location("1623"),
                        description = "description"
                )

        assertEquals("description", FilterValue(FilterField.DESCRIPTION).get(asset))
    }

    @Test
    fun `testGetWithId`() {
        val asset = ConcreteAsset(id = "asset1")
        val filterParameter = FilterValue(FilterField.ID)
        assertEquals("asset1", filterParameter.get(asset))
    }

    @Test
    fun `testGetWithStatus`() {
        val asset = ConcreteAsset(id = "AS-01", status = AssetStatus.UNDEPLOYABLE)
        val filterParameter = FilterValue(FilterField.STATUS)
        assertEquals(AssetStatus.UNDEPLOYABLE, filterParameter.get(asset))
    }

    @Test
    fun `testGetWithAssignee`() {
        val asset = ConcreteAsset(id = "AS-01", assignee = "Alice")
        val filterParameter = FilterValue(FilterField.ASSIGNEE)
        assertEquals("Alice", filterParameter.get(asset))
    }

    @Test
    fun `testGetWithAuditDate`() {
        val auditDate = Date.ofNow()
        val asset = ConcreteAsset(id = "AS-01", auditDate = auditDate)
        val filterParameter = FilterValue(FilterField.AUDIT_DATE)
        assertEquals(auditDate, filterParameter.get(asset))
    }

    // @Test
    // fun `testGetWithLocation`() {
    //     val location = Location("1623")
    //     val asset = ConcreteAsset(id = "AS-01", location = location)
    //     val filterParameter = FilterValue(FilterField.LOCATION)
    //     assertEquals(location, filterParameter.get(asset))
    // }

    @Test
    fun `testToNumberWithAuditDate`() {
        val auditDate = Date.ofNow()
        val asset = ConcreteAsset(id = "AS-01", auditDate = auditDate)
        val filterParameter = FilterValue(FilterField.AUDIT_DATE)
        assertEquals(auditDate.get(), filterParameter.toNumber(asset))
    }

    @Test
    fun `testToStringWithStatus`() {
        val asset = ConcreteAsset(id = "AS-01", status = AssetStatus.UNDEPLOYABLE)
        val filterParameter = FilterValue(FilterField.STATUS)

        assertEquals("Undeployable", filterParameter.toString(asset))
    }
}
