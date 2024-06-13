import org.exotechasset.exotechasset.entity.*
import org.exotechasset.exotechasset.usecase.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FilterHandlerTest {
    private val assetHandler = AssetHandler(listOf(ConcreteAsset("AS-01"), ConcreteAsset("AS-02"), ConcreteAsset("AS-03")))

    //     @Test
    //     fun testFilterHandler() {
    //         val filterHandler = FilterHandler(this.assetList)
    //         val filter = Filter("Category", "Electronics")
    //
    //         assertEquals(0, filterHandler.size())
    //
    //         filterHandler.addFilter(filter)
    //
    //         assertEquals(1, filterHandler.size())
    //         assertEquals(emptyList<Filter>(), filterHandler.getFilterList())
    //     }
    @Test
    fun testGetSize() {
        val filterHandler = FilterHandler(this.assetHandler)

        assertEquals(0, filterHandler.size())
    }

    @Test
    fun testGetFilterList() {
        val filterHandler = FilterHandler(this.assetHandler)

        assertEquals(emptyList<Filter>(), filterHandler.getFilterList())
    }

    @Test
    fun testAddFilter() {
        val filterHandler = FilterHandler(this.assetHandler)
        val filter = EqualsToFilter(FilterField.ID, "AS-01")

        filterHandler.addFilter(filter)

        assertEquals(listOf(filter), filterHandler.getFilterList())
    }

    @Test
    fun testFilter() {
        val assetList2 =
                listOf(
                        ConcreteAsset("AS-01", auditDate = Date(1714402762)),
                        ConcreteAsset("AS-02", auditDate = Date(1717171200)),
                        ConcreteAsset("AS-03", auditDate = Date(1969632000))
                )

        val filterHandler = FilterHandler(AssetHandler(assetList2))

        filterHandler.addFilter(GreaterThanFilter(FilterField.AUDIT_DATE, 1714402763.toLong()))
        filterHandler.addFilter(LessThanFilter(FilterField.AUDIT_DATE, 1969631000.toLong()))

        assertEquals(1, filterHandler.filterAsset().size())
        assertEquals(assetList2[1].getId(), filterHandler.filterAsset().getChildren()[0].getId())
    }
}
