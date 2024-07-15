package de.xyndra.planned

enum class TODOState {
    PLANNED,
    TODO,
    URGENT,
    VERY_URGENT,
    DONE
}

interface TODOInterface {
    var title: String
    var state: TODOState
    fun checkForStateChange()
}