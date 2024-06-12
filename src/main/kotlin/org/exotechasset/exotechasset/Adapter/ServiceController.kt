package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.usecase.AssetHandler
import org.exotechasset.exotechasset.usecase.FilterChain
import org.exotechasset.exotechasset.usecase.ExporterImporterHandler


object ServiceController {
    public val assetHandler: AssetHandler = AssetHandler()
    public val filterChain: FilterChain = FilterChain(this.assetHandler)
    public val exporterImporterHandler: ExporterImporterHandler = ExporterImporterHandler(assetHandler)
}
