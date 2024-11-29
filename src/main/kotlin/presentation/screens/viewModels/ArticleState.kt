package presentation.screens.viewModels

import androidx.compose.runtime.Stable
import domain.models.Article
import domain.utils.ConnectionStatus

@Stable
data class ArticleState(
    val list: List<Article> = emptyList(),
    val status: ConnectionStatus = ConnectionStatus.LOADING
)