import org.exotechasset.exotechasset.entity.*
import org.exotechasset.exotechasset.usecase.*
 import org.junit.jupiter.api.Assertions.assertEquals
 import org.junit.jupiter.api.Test

 internal class FilterChainTest {
     private val assetList = AssetList(listOf(Asset("AS-01"), Asset("AS-02"), Asset("AS-03")))

//     @Test
//     fun testFilterChain() {
//         val filterChain = FilterChain(this.assetList)
//         val filter = Filter("Category", "Electronics")
//
//         assertEquals(0, filterChain.size())
//
//         filterChain.addFilter(filter)
//
//         assertEquals(1, filterChain.size())
//         assertEquals(emptyList<Filter>(), filterChain.getFilterList())
//     }
    @Test
    fun testGetSize() {
        val filterChain = FilterChain(this.assetList)

        assertEquals(0, filterChain.size())
    }

     @Test
     fun testGetFilterList() {
         val filterChain = FilterChain(this.assetList)

         assertEquals(emptyList<Filter>(), filterChain.getFilterList())
     }

     @Test
     fun testAddFilter() {
         val filterChain = FilterChain(this.assetList)
         val filter = EqualsToFilter(listOf(
            FilterParameter(FilterParameterBy.ID, null),
            FilterParameter(FilterParameterBy.VALUE, "AS-01")
        ))

         filterChain.addFilter(filter)

         assertEquals(listOf(filter), filterChain.getFilterList())
     }

     @Test
     fun testFilter() {
         val assetList2 = listOf(
             Asset("AS-01", auditDate = Date(1714402762)),
             Asset("AS-02", auditDate = Date(1717171200)),
             Asset("AS-03", auditDate = Date(1969632000))
         )

         val filterChain = FilterChain(AssetList(assetList2))

         val filter = GreaterThanFilter(listOf(
            FilterParameter(FilterParameterBy.AUDIT_DATE, null), 
            FilterParameter(FilterParameterBy.VALUE, 1714402763.toLong())
        ))
         val filter2 =
             LessThanFilter(listOf(
                 FilterParameter(FilterParameterBy.AUDIT_DATE, null),
                 FilterParameter(FilterParameterBy.VALUE, 1969631000.toLong())
             ))
         filterChain.addFilter(filter)
         filterChain.addFilter(filter2)

         assertEquals(listOf(assetList2[1])[0].getId(), filterChain.filterAsset().getChildren()[0].getId())
     }

    // @Test
    // fun testRemoveFilter() {
    //     val filterChain = FilterChain(this.assetList)
    //     val filter = EqualsToFilter(listOf(
    //         FilterParameter(FilterParameterBy.ID, null),
    //         FilterParameter(FilterParameterBy.VALUE, "AS-01")
    //     ))
    //     filterChain.addFilter(filter)

    //     filterChain.removeF(filter)

    //     assertEquals(0, filterChain.size())
    //     assertEquals(emptyList<Filter>(), filterChain.getAll())
    // }
 }
