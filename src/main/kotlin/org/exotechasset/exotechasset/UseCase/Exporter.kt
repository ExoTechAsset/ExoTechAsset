package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.entity.Report
import org.exotechasset.exotechasset.usecase.AssetHandler

import java.io.File

class Exporter(val assetHandler:AssetHandler) {
    
    public fun export(filePath:String): AssetListFile{
//    public fun export(exportType: ReportType = ReportType.CSV, filePath:String){

        var assetListFile:AssetListFile = AssetListFile(filePath)
        var report = ReportFactory().generate(ReportType.CSV)
        assetHandler.accept(report)
        val result:String = report.get().toString()

        assetListFile.write(result)
        return assetListFile
    }
}