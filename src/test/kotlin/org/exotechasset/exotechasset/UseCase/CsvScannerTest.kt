// package org.exotechasset.exotechasset.UseCase

// import org.exotechasset.exotechasset.usecase.AssetListFile
// import org.exotechasset.exotechasset.usecase.CsvScanner
// import org.junit.jupiter.api.Test

// import org.junit.jupiter.api.Assertions.*
// import org.junit.jupiter.api.BeforeEach

// class CsvScannerTest {
//     @Test
//     fun hasNext() {
//         val csvScanner = CsvScanner()
//         csvScanner.get(assetListFile)
//         kotlin.test.assertEquals(true, csvScanner.hasNext())
//     }

//     @Test
//     fun getNextToken() {
//         val csvScanner = CsvScanner()
//         csvScanner.get(assetListFile)
//         val expect_id = "asset"
//         val expect_status = "Deployable"
//         val expect_assignee = "null"
//         val expect_auditDate = "null"
//         val expect_location = ""
//         val expect_changelog = "[]"
//         val expect_parentId = ""
//         kotlin.test.assertEquals("id", csvScanner.getNextToken())
//         kotlin.test.assertEquals("status", csvScanner.getNextToken())
//         kotlin.test.assertEquals("assignee", csvScanner.getNextToken())
//         kotlin.test.assertEquals("auditDate", csvScanner.getNextToken())
//         kotlin.test.assertEquals("location", csvScanner.getNextToken())
//         kotlin.test.assertEquals("changelog", csvScanner.getNextToken())
//         kotlin.test.assertEquals("parentId", csvScanner.getNextToken())
//         kotlin.test.assertEquals(expect_id, csvScanner.getNextToken())
//         kotlin.test.assertEquals(expect_status, csvScanner.getNextToken())
//         kotlin.test.assertEquals(expect_assignee, csvScanner.getNextToken())
//         kotlin.test.assertEquals(expect_auditDate, csvScanner.getNextToken())
//         kotlin.test.assertEquals(expect_location, csvScanner.getNextToken())
//         kotlin.test.assertEquals(expect_changelog, csvScanner.getNextToken())
//         kotlin.test.assertEquals(expect_parentId, csvScanner.getNextToken())
//     }


// }