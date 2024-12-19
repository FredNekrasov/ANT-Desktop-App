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
import presentation.core.FredCard
import presentation.core.FredIconButton
import presentation.screens.viewModels.PagedArticleState

/**
 * ContentList - list of articles with [FredCard] component.
 *  @param state is a state of articles
 *  @param getArticles is a function that is called to get articles from the server/database
 */
@Composable
internal fun ContentList(
    state: PagedArticleState,
    getArticles: (Int) -> Unit
) {
    var isShowDialog by remember { mutableStateOf(false) }
    var articleIndex by remember { mutableIntStateOf(0) }
    var page by remember { mutableIntStateOf(1) }
    LazyVerticalStaggeredGrid(StaggeredGridCells.Fixed(2), Modifier.fillMaxSize()) {
        itemsIndexed(state.map[page] ?: emptyList(), key = { _, it -> it.id }) { index, it ->
            FredCard(
                onClick = { articleIndex = index; isShowDialog = true },
                uri = it.content.getOrNull(0),
                title = it.title,
                date = it.date
            )
        }
        item {
            Row(Modifier.fillMaxWidth().padding(8.dp), Arrangement.Center, Alignment.CenterVertically) {
                FredIconButton({ getArticles(--page) }, Icons.AutoMirrored.Default.ArrowBack, enabled = page > 1)
                FredIconButton({ getArticles(++page) }, Icons.AutoMirrored.Default.ArrowForward, enabled = page <= 1 && state.hasNextData)
            }
        }
    }
    if(isShowDialog) ListItemDialog(isShowDialog = { isShowDialog = it }, article = state.map[page]!![articleIndex])
}