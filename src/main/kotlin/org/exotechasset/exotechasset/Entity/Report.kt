package org.exotechasset.exotechasset.entity

abstract class Report  (metrics: Metric = Metric()): AbstractVisitor {
    public var metrics:Metric = metrics
}