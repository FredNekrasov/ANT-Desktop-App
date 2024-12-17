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
import presentation.core.*
import presentation.core.components.ImageSlider
import presentation.screens.viewModels.MainArticleState

@Composable
fun Priesthood(state: MainArticleState) {
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        state.list.fastForEach {
            if(it.articleType != ANTStrings.PRIESTHOOD) return@fastForEach
            FredTitle(it.articleType, 26.sp)
            Spacer(Modifier.height(4.dp))
            HorizontalDivider()
            Row(Modifier.fillMaxSize().padding(top = 4.dp)) {
                ImageSlider(article = it, Modifier.fillMaxHeight().wrapContentWidth())
                Spacer(Modifier.width(4.dp))
                VerticalDivider()
                Spacer(Modifier.width(8.dp))
                Column(Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {
                    FredTitle(it.title, 20.sp)
                    Spacer(Modifier.height(4.dp))
                    FredText(it.description, textUnit = 20.sp)
                }
            }
        }
    }
}