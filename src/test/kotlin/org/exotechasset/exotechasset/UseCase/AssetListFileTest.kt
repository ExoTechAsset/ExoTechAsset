package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.useCase.AssetListFile
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.math.exp

class AssetListFileTest {

    @Test
    fun writeFileTest() {
        val assetListFile = AssetListFile("./test.csv")
        val expect = "id, status, assignee, auditDate, location, changelog\n" +
                "asset, Deployable, null, null, , []\n"
        assetListFile.write(expect)
        assetListFile.readCsv()
        val result = assetListFile.getContent()

        assertEquals(expect, result)
    }

    @Test
    fun writeWrongFileTest() {
        val assetListFile = AssetListFile("./test.csv")
        val expect = "id, 123\n" +
                "asset, null\n"
        assertThrows(IllegalArgumentException::class.java) {
            assetListFile.write(expect)
        }
        assertNotEquals(expect, File("./test.csv").readText())
    }
//    @Test
//    fun readFileTest(){
//        val assetListFile = AssetListFile("read_test.csv")
//        val expect = "\"Year\", \"Score\", \"Title\"\n" +
//                "1968,  86, \"Greetings\"\n" +
//                "1970,  17, \"Bloody Mama\"\n" +
//                "1970,  73, \"Hi, Mom!\""
//
//        assetListFile.readCsv()
//
//        assertEquals(expect, assetListFile.getContent())
//    }
}