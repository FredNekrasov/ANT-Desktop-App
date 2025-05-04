package presentation.screens.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.ArticleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class ArticleVM(private val repository: ArticleRepository) : ViewModel() {
    private val articlesMSF = MutableStateFlow(PagedArticleState())
    val articlesSF = articlesMSF.asStateFlow()
    fun getArticles(catalogId: Long, pageNumber: Int = 1) {
        viewModelScope.launch {
            articlesMSF.update { it.copy(isLoading = true) }
            repository.getList(catalogId, pageNumber.toLong()).onSuccess { list ->
                articlesMSF.update {
                    if(list.size < 50) it.copy(map = mapOf(pageNumber to list), isLoading = false)
                    else it.copy(map = mapOf(pageNumber to list), isLoading = false, hasNextData = true)
                }
            }.onFailure {
                articlesMSF.update { it.copy(isLoading = false, hasError = true) }
            }
        }
    }
}
class ParishLifeVM(repository: ArticleRepository) : ArticleVM(repository) {
    fun getParishLifeArticles(pageNumber: Int = 1) = super.getArticles(2, pageNumber)
    init { getArticles(2) }
}
class YouthClubVM(repository: ArticleRepository) : ArticleVM(repository) {
    fun getYouthClubArticles(pageNumber: Int = 1) = super.getArticles(5, pageNumber)
    init { getArticles(5) }
}
class AdvicesVM(repository: ArticleRepository) : ArticleVM(repository) {
    fun getAdviceArticles(pageNumber: Int = 1) = super.getArticles(7, pageNumber)
    init { getArticles(7) }
}
class HistoryVM(repository: ArticleRepository) : ArticleVM(repository) {
    fun getHistoryArticles(pageNumber: Int = 1) = super.getArticles(8, pageNumber)
    init { getArticles(8) }
}
class StoriesVM(repository: ArticleRepository) : ArticleVM(repository) {
    fun getStoryArticles(pageNumber: Int = 1) = super.getArticles(13, pageNumber)
    init { getArticles(13) }
}