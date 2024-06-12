package org.exotechasset.exotechasset.adapter

import org.exotechasset.exotechasset.usecase.AssetListFile

class WebFileTransmission: AssetListFile {
    var text:String = ""

    // TODO
    override public fun read():String = ""

    override public fun write(text:String) {
        this.text = text
    }

    public fun getWriteResponse() = this.text
}