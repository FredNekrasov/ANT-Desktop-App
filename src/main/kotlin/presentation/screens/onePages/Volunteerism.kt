package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import presentation.core.*
import presentation.core.components.ImageSlider
import presentation.screens.viewModels.MainArticleState

@Composable
fun Volunteerism(state: MainArticleState) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(8.dp)) {
        state.list.fastForEach {
            if(it.articleType != ANTStrings.VOLUNTEERISM) return@fastForEach
            FredTitle(it.articleType, 20.sp)
            Spacer(Modifier.height(8.dp))
            FredTitle(it.title,20.sp)
            Spacer(Modifier.height(4.dp))
            FredText(it.description, textUnit = 20.sp)
            Spacer(Modifier.height(8.dp))
            ImageSlider(it, Modifier.fillMaxWidth().aspectRatio(2.75f))
        }
    }
}