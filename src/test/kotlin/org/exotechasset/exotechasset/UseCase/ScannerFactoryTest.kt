package org.exotechasset.exotechasset.UseCase

import org.exotechasset.exotechasset.useCase.AssetListFile
import org.exotechasset.exotechasset.useCase.CsvScanner
import org.exotechasset.exotechasset.useCase.ScannerFactory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ScannerFactoryTest {

    @Test
    fun getCsvScanner() {
        val scannerFactory = ScannerFactory()
        val assetListFile = AssetListFile("./test.csv")
        val scanner = scannerFactory.get(assetListFile)
        assertTrue(scanner is CsvScanner)
    }
}