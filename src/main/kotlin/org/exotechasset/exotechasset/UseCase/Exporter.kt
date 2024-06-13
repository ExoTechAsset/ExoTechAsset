package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Report

import java.io.File

class Exporter(val assetHandler:AssetHandler) {
    
    public fun export(): String{
        var reportVisitor = CsvVisitor()
        assetHandler.accept(reportVisitor)
        return reportVisitor.get()
    }
}
