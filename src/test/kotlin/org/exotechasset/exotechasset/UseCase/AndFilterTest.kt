import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.usecase.AndFilter
import org.exotechasset.exotechasset.usecase.FilterParameter
import org.exotechasset.exotechasset.usecase.FilterParameterBy as By
import org.exotechasset.exotechasset.usecase.GreaterThanFilter
import org.exotechasset.exotechasset.usecase.LessThanFilter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AndFilterTest {
    private val assetList =
            listOf(
                    Asset("AS-01", auditDate = Date(1714402762)),
                    Asset("AS-02", auditDate = Date(1717171200)),
                    Asset("AS-03", auditDate = Date(1969632000))
            )

    @Test
    fun testMeet() {
        val filter1 = GreaterThanFilter(listOf(
                FilterParameter(By.AUDIT_DATE, null),
                FilterParameter(By.VALUE, 1717170000.toLong())
        ))

        val filter2 = LessThanFilter(listOf(
                FilterParameter(By.AUDIT_DATE, null),
                FilterParameter(By.VALUE, 1814406400.toLong())
        ))

        val andFilter = AndFilter(listOf(filter1, filter2))
        val filteredAssets = andFilter.meet(assetList)

        assertEquals(listOf(this.assetList[1]), filteredAssets)
    }
}
