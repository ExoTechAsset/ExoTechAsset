import org.exotechasset.exotechasset.entity.ConcreteAsset
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.entity.FilterField
import org.exotechasset.exotechasset.usecase.GreaterThanOrEqualsToFilter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GreaterThanOrEqualsToFilterTest {
        private val assetList =
                        listOf(
                                        ConcreteAsset("AS-01", auditDate = Date(1714402762)),
                                        ConcreteAsset("AS-02", auditDate = Date(1717171200)),
                                        ConcreteAsset("AS-03", auditDate = Date(1969632000))
                        )

        @Test
        fun testMeet() {
                val filter =
                                GreaterThanOrEqualsToFilter(
                                                FilterField.AUDIT_DATE,
                                                1717171200.toLong()
                                )
                val filteredAssets = filter.meet(assetList)

                assertEquals(listOf(assetList[1], assetList[2]), filteredAssets)
        }
}
