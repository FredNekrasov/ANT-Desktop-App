package presentation.screens.viewModels

import domain.repositories.IArticleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ArticleVM(
    private val repository: IArticleRepository,
) {
    private val articlesMSF = MutableStateFlow(ArticleState())
    val articlesSF = articlesMSF.asStateFlow()
    fun getArticles(catalogId: Int, pageNumber: Int = 1) {
        CoroutineScope(Dispatchers.Default).launch {
            repository.getList(catalogId, pageNumber).flowOn(Dispatchers.IO).collectLatest {
                articlesMSF.emit(articlesSF.value.copy(list = it.list, status = it.status))
            }
        }
    }
//    init {
//        getArticles(1)
//        getArticles(2)
//        getArticles(3)
//        getArticles(5)
//        getArticles(6)
//        getArticles(7)
//        getArticles(8)
//        getArticles(9)
//        getArticles(10)
//        getArticles(12)
//        getArticles(13)
//    }
}