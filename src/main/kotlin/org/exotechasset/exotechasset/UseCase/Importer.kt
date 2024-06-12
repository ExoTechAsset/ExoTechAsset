package org.exotechasset.exotechasset.useCase

import org.exotechasset.exotechasset.UseCase.Builder
import org.exotechasset.exotechasset.entity.AbstractVisitor
import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.usecase.AssetList

class Importer {
    private var builder: Builder = Builder()
    val ATTRIBUTE_NAMES = listOf("id", "status", "assignee", "auditDate", "location", "changelog", "assetDescription")
    // TODO: change assetList to assetHandler
    public fun import(assetList: AssetList, assetListFile: AssetListFile):AssetList{
        val scannerFactory = ScannerFactory()
        val scanner = scannerFactory.get(assetListFile)
        while(scanner.hasNext()){
            val asset = buildAsset(scanner)
            assetList.addNewAsset(asset)
        }
        return assetList
    }
    fun buildAsset(scanner: AbstractScanner):Asset{
        if (!scanner.hasNext()) {
            throw IllegalArgumentException("No more tokens")
        }
        var token:String = scanner.getNextToken()
        while(token in ATTRIBUTE_NAMES && scanner.hasNext()) {
            token = scanner.getNextToken()
        }
        builder.createNewAsset()
        builder.buildAssetId(token)
        token = scanner.getNextToken()
        builder.buildAssetStatus(token)
        token = scanner.getNextToken()
        builder.buildAssetAssignee(token)
        token = scanner.getNextToken()
        builder.buildAssetAuditDate(token)
        token = scanner.getNextToken()
        builder.buildAssetLocation(token)
        token = scanner.getNextToken()
//        buildAssetChangelog(token)
        //token = scanner.getNextToken()
//        buildparentAsset(token)
        val asset = builder.get()
        return asset
    }
}