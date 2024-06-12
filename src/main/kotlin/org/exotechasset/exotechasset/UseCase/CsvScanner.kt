package org.exotechasset.exotechasset.usecase

import java.util.*

class CsvScanner: AbstractScanner() {
    // TODO: remove
    public override fun get(assetListFile: AssetListFile){}
    public override fun getNextToken(): String = ""
    public override fun hasNext(): Boolean = true
//    "TODO: String format?"
    // private var assetListFile: AssetListFile
    // private var scanner: Scanner = Scanner("")
    // public override fun get(assetListFile: AssetListFile){
    //     // TODO
    //     this.assetListFile = assetListFile
    //     scanner = Scanner(assetListFile.read())
    //     scanner.useDelimiter(", |\\n")
    // }
    // public override fun getNextToken(): String{
    //     // TODO
    //     val token = scanner.next()
    //     return token
    // }
    // public override fun hasNext(): Boolean{
    //     // TODO
    //     if (scanner.hasNext()){
    //         return true
    //     }
    //     return false
    // }
}
