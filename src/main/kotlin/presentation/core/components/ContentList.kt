package presentation.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.core.ANTCard
import presentation.core.ANTIconButton
import presentation.screens.viewModels.PagedArticleState

/**
 * Displays a list of articles using the [ANTCard] component in a staggered grid format.
 *
 * @param state The state containing the paged articles to be displayed.
 * @param getArticles A lambda function to fetch articles for the specified page.
 *
 * The list is presented in a lazy staggered grid, with each article displayed as a card.
 * Clicking on an article card will open a dialog showing the article details.
 * Pagination controls allow navigating between pages of articles.
 */
@Composable
internal fun ContentList(
    state: PagedArticleState,
    getArticles: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isShowDialog by remember { mutableStateOf(false) }
    var articleIndex by remember { mutableIntStateOf(0) }
    var page by remember { mutableIntStateOf(1) }
    LazyVerticalStaggeredGrid(StaggeredGridCells.Fixed(2), modifier.padding(8.dp)) {
        itemsIndexed(state.map[page] ?: emptyList(), key = { _, it -> it.id }) { index, it ->
            ANTCard(
                onClick = { articleIndex = index; isShowDialog = true },
                uri = it.content.getOrNull(0),
                title = it.title,
                date = it.dateOrBanner
            )
        }
        item {
            Row(Modifier.fillMaxWidth().padding(8.dp), Arrangement.Center, Alignment.CenterVertically) {
                ANTIconButton({ getArticles(--page) }, Icons.AutoMirrored.Default.ArrowBack, enabled = page > 1)
                ANTIconButton({ getArticles(++page) }, Icons.AutoMirrored.Default.ArrowForward, enabled = page <= 1 && state.hasNextData)
            }
        }
    }
    if(isShowDialog) ListItemDialog(
        isShowDialog = { isShowDialog = it },
        article = state.map[page]!![articleIndex],
        modifier = modifier
    )
}