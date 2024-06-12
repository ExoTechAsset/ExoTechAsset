package org.exotechasset.exotechasset.useCase

abstract class AbstractScanner {
    //    "TODO: get(String format)?"
    public abstract fun get(assetListFile: AssetListFile);
    public abstract fun getNextToken(): String;
    public abstract fun hasNext(): Boolean;
}