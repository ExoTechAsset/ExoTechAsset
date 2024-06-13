package org.exotechasset.exotechasset.entity

import org.exotechasset.exotechasset.entity.AssetStatus.*

class ConcreteAsset(
        private var id: String,
        private var status: AssetStatus = DEPLOYABLE,
        private var assignee: String? = null,
        private var auditDate: Date? = null,
        private var location: Location? = null,
        private var changelog: Changelog = Changelog(),
        private var description: String = "",
        private var parentId: String? = null
) : Asset(id, status, assignee, auditDate, location, changelog, description, parentId) {
}
