package org.exotechasset.exotechasset.useCase

import java.util.*

class CsvScanner: AbstractScanner() {
//    "TODO: String format?"
    private var assetListFile: AssetListFile = AssetListFile("")
    private var scanner: Scanner = Scanner("")
    public override fun get(assetListFile: AssetListFile){
        // TODO
        this.assetListFile = assetListFile
        scanner = Scanner(assetListFile.getContent())
        scanner.useDelimiter(", |\\n")
    }
//    public override fun getNextToken(): String{
//        // TODO
//        val token = scanner.nextLine()
//        return token
//    }
    public override fun getNextToken(): String{
        // TODO
        val token = scanner.next()
        return token
    }
    public override fun hasNext(): Boolean{
        // TODO
        if (scanner.hasNext()){
            return true
        }
        return false
    }
}