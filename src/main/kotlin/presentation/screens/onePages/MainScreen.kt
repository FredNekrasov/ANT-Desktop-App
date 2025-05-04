package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import data.ArticleDto
import presentation.core.*
import presentation.core.components.ImageSlider
import presentation.screens.viewModels.MainArticleState

@Composable
fun MainScreen(
    state: MainArticleState,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(8.dp)) {
        state.list.fastForEach {
            if(it.catalog.name != ANTStrings.MAIN) return@fastForEach
            ANTTitle(it.catalog.name)
            Spacer(Modifier.height(4.dp))
            ANTText(it.dateOrBanner)
            Spacer(Modifier.height(4.dp))
            HorizontalDivider()
            MainContent(it)
        }
    }
}
/**
 * A composable function that displays the main content of a page.
 *
 * The content is presented as a row containing:
 *  - an image with the image from the article
 *  - a vertical divider
 *  - a column containing the title and description of the article
 *
 * @param article the article to be displayed
 * @param modifier the modifier to be applied to the composable
 */
@Composable
fun MainContent(
    article: ArticleDto,
    modifier: Modifier = Modifier
) {
    Row(Modifier.padding(top = 4.dp)) {
        ImageSlider(article)
        Spacer(Modifier.width(4.dp))
        VerticalDivider()
        Column(Modifier.fillMaxHeight().padding(horizontal = 4.dp).verticalScroll(rememberScrollState())) {
            ANTTitle(article.title)
            Spacer(Modifier.height(4.dp))
            ANTText(article.description)
        }
    }
}