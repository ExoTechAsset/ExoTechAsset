package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.usecase.AssetHandler
import org.exotechasset.exotechasset.usecase.AssetList

class ExporterImporterHandler(val assetHandler:AssetHandler) {

    public fun exportFile(file:AssetListFile){
        var exporter = Exporter(this.assetHandler)
        val content:String = exporter.export()
        file.write(content)
    }

    public fun importFile(assetListFile: AssetListFile): AssetList{
        var importer = Importer(this.assetHandler)
        return importer.import(assetListFile)
    }
}