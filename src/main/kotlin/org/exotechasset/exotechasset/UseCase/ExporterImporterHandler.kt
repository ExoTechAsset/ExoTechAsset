package org.exotechasset.exotechasset.useCase

import org.exotechasset.exotechasset.usecase.AssetList

class ExporterImporterHandler {
    //ToDO Change AssetList to AssetHandler
    public fun exportFile(filePath:String, assetList: AssetList): AssetListFile{
        var exporter = Exporter()
        return exporter.export(filePath, assetList)
    }
    public fun importFile(assetList: AssetList, assetListFile: AssetListFile): AssetList{
        var importer = Importer()
        return importer.import(assetList, assetListFile)
    }
}