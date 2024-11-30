package presentation.screens

import androidx.compose.runtime.Composable
import presentation.core.ANTStrings
import presentation.core.components.ContentList
import presentation.screens.viewModels.ArticleState

@Composable
fun ParishLife(state: ArticleState, getArticles: (Int, Int) -> Unit) {
    ContentList(state, getArticles, ANTStrings.PARISH_LIFE)
}
@Composable
fun YouthClub(state: ArticleState, getArticles: (Int, Int) -> Unit) {
    ContentList(state, getArticles, ANTStrings.YOUTH_CLUB)
}
@Composable
fun Advices(state: ArticleState, getArticles: (Int, Int) -> Unit) {
    ContentList(state, getArticles, ANTStrings.ADVICES)
}
@Composable
fun History(state: ArticleState, getArticles: (Int, Int) -> Unit) {
    ContentList(state, getArticles, ANTStrings.HISTORY)
}
@Composable
fun Stories(state: ArticleState, getArticles: (Int, Int) -> Unit) {
    ContentList(state, getArticles, ANTStrings.STORIES)
}