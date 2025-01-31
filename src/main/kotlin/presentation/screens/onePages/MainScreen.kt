package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import domain.models.Article
import presentation.core.*
import presentation.core.components.ImageSlider
import presentation.screens.viewModels.MainArticleState

@Composable
fun MainScreen(state: MainArticleState) {
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        state.list.fastForEach {
            if(it.articleType != ANTStrings.MAIN) return@fastForEach
            FredTitle(it.articleType, textUnit = 20.sp)
            Spacer(Modifier.height(4.dp))
            FredText(it.date, modifier = Modifier.fillMaxWidth(), textUnit = 20.sp)
            Spacer(Modifier.height(4.dp))
            HorizontalDivider()
            MainContent(it)
        }
    }
}
/**
 * Display the content of the main page and the priesthood page
 */
@Composable
fun MainContent(article: Article) {
    Row(Modifier.fillMaxSize().padding(top = 4.dp)) {
        ImageSlider(article, Modifier.fillMaxHeight().wrapContentWidth())
        Spacer(Modifier.width(4.dp))
        VerticalDivider()
        Spacer(Modifier.width(8.dp))
        Column(Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {
            FredTitle(article.title, 20.sp)
            Spacer(Modifier.height(4.dp))
            FredText(article.description, textUnit = 20.sp)
        }
    }
}