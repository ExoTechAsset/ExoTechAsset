package org.exotechasset.exotechasset.entity

interface AbstractVisitor {
    public abstract fun visit(asset:Asset);
    public abstract fun get():Any;
}