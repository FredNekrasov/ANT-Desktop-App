package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import presentation.core.*
import presentation.core.components.ImageSlider
import presentation.screens.viewModels.MainArticleState

@Composable
fun Volunteerism(
    state: MainArticleState,
    modifier: Modifier = Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState()).padding(8.dp)) {
        state.list.fastForEach {
            if(it.catalog.name != ANTStrings.VOLUNTEERISM) return@fastForEach
            ANTTitle(it.catalog.name)
            Spacer(Modifier.height(8.dp))
            ANTTitle(it.title)
            Spacer(Modifier.height(4.dp))
            ANTText(it.description)
            Spacer(Modifier.height(8.dp))
            ImageSlider(it, Modifier.aspectRatio(2.75f))
        }
    }
}