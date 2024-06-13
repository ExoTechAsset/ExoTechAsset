package org.exotechasset.exotechasset.usecase

import java.util.*

class CsvScanner(val text:String): AbstractScanner() {
    private val splittedStrList:List<String> = text.lines().drop(1)
    private val splittedStrListIterator = splittedStrList.iterator()
    // TODO: improve this mechanism
    private var subList:List<String> = splittedStrListIterator.next().split(",")
    private var subListIterator = subList.iterator()

    private var token:String = ""
    private var state:ScannerState = ScannerState.NEW_LINE

    public override fun get() = this.token
    public override fun getNextToken():String {
        if (this.subListIterator.hasNext()) {
            this.token = this.subListIterator.next()
        } else if (this.splittedStrListIterator.hasNext()) {
            this.subList = this.splittedStrListIterator.next().split(",")
            this.subListIterator = this.subList.iterator()
        } else {
            throw IllegalArgumentException()
        }
        state = state.next() as ScannerState
        return this.token
    }
    public fun getState() = this.state
    public override fun hasNext(): Boolean = ((this.subListIterator.hasNext()) || (splittedStrListIterator.hasNext()))
}
