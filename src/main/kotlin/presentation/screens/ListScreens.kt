package presentation.screens

import androidx.compose.runtime.Composable
import presentation.core.components.ContentList
import presentation.screens.viewModels.PagedArticleState

@Composable
fun ParishLife(state: PagedArticleState, getArticles: (Int) -> Unit) {
    ContentList(state, getArticles)
}
@Composable
fun YouthClub(state: PagedArticleState, getArticles: (Int) -> Unit) {
    ContentList(state, getArticles)
}
@Composable
fun Advices(state: PagedArticleState, getArticles: (Int) -> Unit) {
    ContentList(state, getArticles)
}
@Composable
fun History(state: PagedArticleState, getArticles: (Int) -> Unit) {
    ContentList(state, getArticles)
}
@Composable
fun Stories(state: PagedArticleState, getArticles: (Int) -> Unit) {
    ContentList(state, getArticles)
}