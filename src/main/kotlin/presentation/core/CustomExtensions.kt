package presentation.core

import domain.utils.ConnectionStatus
import domain.utils.ConnectionStatus.*

fun ConnectionStatus.getMessage(): String = when(this) {
    CONNECTION_ERROR -> ANTStrings.CONNECTION_ERROR
    NO_INTERNET -> ANTStrings.NO_INTERNET
    NO_DATA -> ANTStrings.NO_DATA
    SERIALIZATION_ERROR -> ANTStrings.SERIALIZATION_ERROR
    UNKNOWN -> ANTStrings.UNKNOWN
    else -> ""
}
fun ConnectionStatus.isError(): Boolean = (this != SUCCESS) && (this != LOADING)
fun ConnectionStatus.isLoading(): Boolean = this == LOADING
fun List<String>.getNotNull(index: Int): String = this.getOrNull(index).toString()
typealias Action = () -> Unit