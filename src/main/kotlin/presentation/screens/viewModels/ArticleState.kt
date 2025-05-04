package presentation.screens.viewModels

import androidx.compose.runtime.Stable
import data.ArticleDto

@Stable
data class MainArticleState(
    val list: List<ArticleDto> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)
@Stable
data class PagedArticleState(
    val map: Map<Int, List<ArticleDto>> = emptyMap(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val hasNextData: Boolean = false
)