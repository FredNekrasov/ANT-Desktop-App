package presentation.screens.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.ArticleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainVM(private val repository: ArticleRepository) : ViewModel() {
    private val articlesMSF = MutableStateFlow(MainArticleState())
    val articlesSF = articlesMSF.asStateFlow()
    private fun getArticles(catalogId: Long, pageNumber: Long = 1) {
        viewModelScope.launch {
            articlesMSF.update { it.copy(isLoading = true) }
            repository.getList(catalogId, pageNumber).onSuccess { list ->
                articlesMSF.update { it.copy(list = list, isLoading = false) }
            }.onFailure {
                articlesMSF.update { it.copy(isLoading = false, hasError = true) }
            }
        }
    }
    init { getArticles(1) }
}