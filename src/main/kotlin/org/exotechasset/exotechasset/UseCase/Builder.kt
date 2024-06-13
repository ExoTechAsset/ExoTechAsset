package org.exotechasset.exotechasset.UseCase

import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.AssetStatus
import org.exotechasset.exotechasset.entity.CompositeAsset
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.entity.Location
import org.exotechasset.exotechasset.usecase.AbstractScanner
import org.exotechasset.exotechasset.usecase.CsvScanner
import java.time.Instant
import java.time.ZonedDateTime


class Builder {
    private lateinit var asset:Asset


    fun createNewAsset(){
        asset = CompositeAsset("")
    }
    fun buildAssetId(id: String){
        asset = CompositeAsset(id)
    }


    fun buildAssetStatus(status: String){
        when(status){
            "Deployable" -> asset.setStatus(AssetStatus.DEPLOYABLE)
            "Pending" -> asset.setStatus(AssetStatus.PENDING)
            "Deployed" -> asset.setStatus(AssetStatus.DEPLOYED)
            "Undeployable" -> asset.setStatus(AssetStatus.UNDEPLOYABLE)
        }
    }
    fun buildAssetLocation(location: String){
        // TODO: use another mechanism to replace this tricky one
        if (location == "null") {
            return
        }
        asset.setLocation(Location(location))
    }
    fun buildAssetAssignee(assignee: String?){
        // TODO: use another mechanism to replace this tricky one
        if (assignee == "null") {
            return
        }
        asset.setAssignee(assignee)
    }
    fun buildAssetAuditDate(auditDate: String){
        // TODO: use another mechanism to replace this tricky one
        if (auditDate == "null") {
            return
        }
        try {
            val zoneDate: ZonedDateTime = ZonedDateTime.parse(auditDate)
            val date = Date(zoneDate.toInstant().epochSecond)
            asset.setAuditDate(date)
        }catch (e: Exception) {
            // Not to set audit date is failed
        }
    }
    fun buildAssetDescription(description: String){
        // TODO: use another mechanism to replace this tricky one
        if (description == "null") {
            return
        }
        asset.setDescription(description)
    }
    fun buildAssetParentId(parentId: String?){
        // TODO: use another mechanism to replace this tricky one
        if (parentId == "null") {
            return
        }
        asset.setParentId(parentId)
    }
    // fun buildAssetChangelog(changelog: String){
    //     // TODO: add codes
    // }
    fun get(): Asset {
        return asset
    }
}
