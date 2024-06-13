package org.exotechasset.exotechasset.usecase

import org.exotechasset.exotechasset.UseCase.Builder
import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.usecase.AssetHandler
import org.exotechasset.exotechasset.usecase.AssetList

class Importer(val assetHandler:AssetHandler) {
    private var builder: Builder = Builder()
    val ATTRIBUTE_NAMES = listOf("id", "status", "assignee", "auditDate", "location", "description", "parentId")

    public fun import(assetListFile: AssetListFile){
        val text:String = assetListFile.read()
        val scanner = CsvScanner(text)
        val builder = Builder()
        while(scanner.hasNext()){
            val token = scanner.getNextToken()
            val state = scanner.getState()
            when(state) {
                ScannerState.NEW_LINE -> {
                    val asset:Asset = builder.get()
                    val result = this.assetHandler.addNewAsset(asset, asset.getParentId())
                }
                ScannerState.ID -> {
                    builder.createNewAsset()
                    builder.buildAssetId(token)
                }
                ScannerState.STATUS -> builder.buildAssetStatus(token)
                ScannerState.ASSIGNEE -> builder.buildAssetAssignee(token)
                ScannerState.AUDIT_DATE -> builder.buildAssetAuditDate(token)
                ScannerState.LOCATION -> builder.buildAssetLocation(token)
                ScannerState.DESCRIPTION -> builder.buildAssetDescription(token)
                ScannerState.PARENT_ID -> builder.buildAssetParentId(token)
            }
        }
    }
}