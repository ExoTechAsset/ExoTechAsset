package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.usecase.AssetListFile

class WebFileTransmission: AssetListFile {
    var writeText:String = ""
    var readText:String = ""

    public fun giveReadString(text:String) {
        this.readText = text
    }

    override public fun read():String = this.readText

    override public fun write(text:String) {
        this.writeText = text
    }

    public fun getWriteResponse() = this.writeText
}