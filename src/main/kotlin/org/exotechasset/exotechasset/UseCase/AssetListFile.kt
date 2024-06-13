package org.exotechasset.exotechasset.usecase

interface AssetListFile {
    fun read():String
    fun write(text:String)
}