import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.usecase.EqualsToFilter
import org.exotechasset.exotechasset.usecase.FilterParameter
import org.exotechasset.exotechasset.usecase.FilterParameterBy as By
import org.exotechasset.exotechasset.usecase.OrFilter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class OrFilterTest {
    private val assetList =
            listOf(
                    Asset("AS-01", auditDate = Date(1714402762)),
                    Asset("AS-02", auditDate = Date(1717171200)),
                    Asset("AS-03", auditDate = Date(1969632000))
            )

    @Test
    fun testMeet() {

        val filter1 =
                EqualsToFilter(listOf(
                        FilterParameter(By.ID, null),
                        FilterParameter(By.VALUE, "AS-01")
                ))
        val filter2 =
                EqualsToFilter(listOf(
                        FilterParameter(By.ID, null),
                        FilterParameter(By.VALUE, "AS-02")
                ))
        val orFilter = OrFilter(listOf(filter1, filter2))
        val filteredAssets = orFilter.meet(assetList)

        assertEquals(listOf(this.assetList[0], this.assetList[1]), filteredAssets)
    }
}
