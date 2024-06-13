package org.exotechasset.exotechasset.usecase

abstract class AbstractScanner {
    //    "TODO: get(String format)?"
    public abstract fun get():Any?;
    public abstract fun getNextToken(): String;
    public abstract fun hasNext(): Boolean;
}