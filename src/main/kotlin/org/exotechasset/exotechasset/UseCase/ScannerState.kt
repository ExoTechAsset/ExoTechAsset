package org.exotechasset.exotechasset.usecase

enum class ScannerState: NextOneInterface {
    ID{
        override public fun next(): ScannerState = ScannerState.STATUS
    },
    STATUS{
        override public fun next(): ScannerState = ScannerState.ASSIGNEE
    },
    ASSIGNEE{
        override public fun next(): ScannerState = ScannerState.AUDIT_DATE
    },
    AUDIT_DATE{
        override public fun next(): ScannerState = ScannerState.LOCATION
    },
    LOCATION{
        override public fun next(): ScannerState = ScannerState.DESCRIPTION
    },
    DESCRIPTION{
        override public fun next(): ScannerState = ScannerState.PARENT_ID
    },
    PARENT_ID{
        override public fun next(): ScannerState = ScannerState.NEW_LINE
    },
    NEW_LINE{
        override public fun next(): ScannerState = ScannerState.ID
    }
}
