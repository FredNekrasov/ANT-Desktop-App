package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import presentation.core.ANTStrings
import presentation.core.FredTitle
import presentation.screens.viewModels.MainArticleState

@Composable
fun Priesthood(state: MainArticleState) {
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        state.list.fastForEach {
            if(it.articleType != ANTStrings.PRIESTHOOD) return@fastForEach
            FredTitle(it.articleType, 26.sp)
            Spacer(Modifier.height(4.dp))
            HorizontalDivider()
            MainContent(it)
        }
    }
}