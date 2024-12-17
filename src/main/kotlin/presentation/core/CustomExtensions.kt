package presentation.core

import androidx.compose.material3.SnackbarHostState
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
suspend fun ConnectionStatus.displayMessage(
    snackbarHostState: SnackbarHostState,
    isDataLoading: (Boolean) -> Unit
) {
    if((this != SUCCESS) && (this != LOADING)) snackbarHostState.showSnackbar(getMessage())
    isDataLoading(this == LOADING)
}
fun List<String>.getNotNull(index: Int): String = this.getOrNull(index) ?: ""
typealias Action = () -> Unit