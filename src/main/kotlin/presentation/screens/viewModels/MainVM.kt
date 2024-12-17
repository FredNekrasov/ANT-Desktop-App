package presentation.screens.viewModels

import domain.repositories.IArticleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainVM(
    private val repository: IArticleRepository
) {
    private val articlesMSF = MutableStateFlow(MainArticleState())
    val articlesSF = articlesMSF.asStateFlow()
    private fun getArticles(catalogId: Int, pageNumber: Int = 1) {
        CoroutineScope(Dispatchers.Default).launch {
            repository.getList(catalogId, pageNumber).flowOn(Dispatchers.IO).collectLatest {
                val newState = articlesSF.value.copy(list = it.list, status = it.status)
                articlesMSF.emit(newState)
            }
        }
    }
    init { getArticles(1) }
}