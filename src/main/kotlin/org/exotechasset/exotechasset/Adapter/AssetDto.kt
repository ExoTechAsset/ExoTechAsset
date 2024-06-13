package org.exotechasset.exotechasset.adapter

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import org.exotechasset.exotechasset.entity.Asset
import org.exotechasset.exotechasset.entity.AssetStatus
import org.exotechasset.exotechasset.entity.CompositeAsset
import org.exotechasset.exotechasset.entity.Date
import org.exotechasset.exotechasset.entity.Location
import org.exotechasset.exotechasset.entity.ToStringInterface
import org.json.JSONObject

data public class AssetDto(
        val id: String,
        val status: String,
        val assignee: String?,
        val auditDate: String?,
        val location: String?,
        // val changelog: String,
        val description: String,
        // val childrenAssets: String
) : ToStringInterface {

    // TODO: private val name: String = ""
    // TODO: private val description: String = ""

    // val id: String = asset.getId(),
    // val status: String = asset.getStatus().toString(),
    // val assignee: String? = asset.getAssignee(),
    // val auditDate: String? = asset.getAuditDate().toString(),
    // val location: String? = asset.getLocation()?.get(),
    // val changelog: List<String> = asset.getChangelog().get(),
    // val description: String = asset.getDescription().getDescription(),
    // val children: List<String> = asset.getChildren().map { it.getId() }

    constructor(
            asset: Asset,
    ) : this(
            asset.getId(),
            asset.getStatus().toString(),
            asset.getAssignee(),
            asset.getAuditDate().toString(),
            asset.getLocation()?.get(),
            //  asset.getChangelog().get().joinToString(prefix = "[", postfix = "]"),
            asset.getDescription(),
            //  asset.getChildren().map { it.getId() }.joinToString(prefix = "[", postfix = "]")
            )

    public fun toJSONObject(): JSONObject {
        val json = JSONObject()
        json.put("id", this.id)
        json.put("status", this.status)
        json.put("assignee", this.assignee)
        json.put("auditDate", this.auditDate)
        json.put("location", this.location)
        // json.put("changelog", this.changelog.joinToString(prefix = "[", postfix = "]"))
        json.put("description", this.description)
        // json.put("children", this.children.joinToString(prefix = "[", postfix = "]"))
        return json
    }

    // TODO: use builder pattern to rewrite the logic.
    public fun toAsset(): Asset {
        var date: Date? = null
        if (this.auditDate != null) {
            try {
                val localDateTime: LocalDateTime = LocalDateTime.parse(this.auditDate, DateTimeFormatter.ISO_DATE_TIME)
                date = Date(localDateTime.toEpochSecond(ZoneOffset.UTC))
            } catch (except: ClassCastException) {
                date = null
            } catch (except: DateTimeParseException) {
                date = null
            }
        }
        // TODO: move the following logic for transferring string into AssetStatus to AssetStatus as
        // a method.
        val status: AssetStatus =
                when (this.status) {
                    "Deployable" -> AssetStatus.DEPLOYABLE
                    "Pending" -> AssetStatus.PENDING
                    "Deployed" -> AssetStatus.DEPLOYED
                    "Undeployable" -> AssetStatus.UNDEPLOYABLE
                    else -> AssetStatus.DEPLOYABLE
                }

        return CompositeAsset(
                this.id,
                status,
                this.assignee,
                date,
                if (this.location == null) null else Location(this.location),
                // Changelog(this.changelog),
                description = this.description
        )
    }

    public override fun toString(): String = this.toJSONObject().toString()
}
