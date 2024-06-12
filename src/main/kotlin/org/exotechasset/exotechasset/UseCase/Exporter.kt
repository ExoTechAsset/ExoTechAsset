package org.exotechasset.exotechasset.useCase

import org.exotechasset.exotechasset.entity.Report
import org.exotechasset.exotechasset.usecase.AssetList
import java.io.File

class Exporter {
    public fun export(filePath:String, assetList: AssetList): AssetListFile{
//    public fun export(exportType: ReportType = ReportType.CSV, filePath:String){

        var assetListFile:AssetListFile = AssetListFile(filePath)
        var report = ReportFactory().generate(ReportType.CSV)
        assetList.accept(report)
        val result:String = report.get().toString()

        assetListFile.write(result)
        return assetListFile
    }
}