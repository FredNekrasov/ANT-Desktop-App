package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import coil3.compose.AsyncImage
import presentation.core.ANTStrings
import presentation.screens.viewModels.MainArticleState

@Composable
fun Schedule(
    state: MainArticleState,
    modifier: Modifier = Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        state.list.fastForEach {
            if(it.catalog.name != ANTStrings.SCHEDULE) return@fastForEach
            AsyncImage(model = it.title, contentDescription = it.title)
            AsyncImage(model = it.description, contentDescription = it.description)
        }
    }
}