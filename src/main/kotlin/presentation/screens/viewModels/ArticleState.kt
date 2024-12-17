package presentation.screens.viewModels

import androidx.compose.runtime.Stable
import domain.models.Article
import domain.utils.ConnectionStatus

@Stable
data class MainArticleState(
    val list: List<Article> = emptyList(),
    val status: ConnectionStatus = ConnectionStatus.LOADING
)
@Stable
data class PagedArticleState(
    val map: Map<Int, List<Article>> = emptyMap(),
    val status: ConnectionStatus = ConnectionStatus.LOADING,
    val hasNextData: Boolean = false
)