package presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import org.koin.core.qualifier.qualifier
import presentation.core.*
import presentation.screens.*
import presentation.screens.onePages.*
import presentation.screens.viewModels.*

@Composable
fun ANTNavHost(
    controller: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier.fillMaxSize(),
    navItems: SnapshotStateList<String> = ANTStrings.screens
) {
    val mainVM: MainVM = koinInject(qualifier<MainVM>())
    val parishLifeVM: ParishLifeVM = koinInject(qualifier<ParishLifeVM>())
    val youthClubVM: YouthClubVM = koinInject(qualifier<YouthClubVM>())
    val advicesVM: AdvicesVM = koinInject(qualifier<AdvicesVM>())
    val historyVM: HistoryVM = koinInject(qualifier<HistoryVM>())
    val storiesVM: StoriesVM = koinInject(qualifier<StoriesVM>())
    val mainArticleState = mainVM.articlesSF.collectAsState().value
    NavHost(navController = controller, startDestination = navItems[0]) {
        composable(navItems[0]) { MainScreen(mainArticleState) }
        composable(navItems[1]) { ParishLife(parishLifeVM.articlesSF.collectAsState().value, parishLifeVM::getParishLifeArticles) }
        composable(navItems[2]) { Schedule(mainArticleState) }
        composable(navItems[3]) { ANTProgressIndicator(modifier) }
        composable(navItems[4]) { YouthClub(youthClubVM.articlesSF.collectAsState().value, youthClubVM::getYouthClubArticles) }
        composable(navItems[5]) { Priesthood(mainArticleState) }
        composable(navItems[6]) { Advices(advicesVM.articlesSF.collectAsState().value, advicesVM::getAdviceArticles) }
        composable(navItems[7]) { History(historyVM.articlesSF.collectAsState().value, historyVM::getHistoryArticles) }
        composable(navItems[8]) { Sacraments(mainArticleState) }
        composable(navItems[9]) { ANTProgressIndicator(modifier) }
        composable(navItems[10]) { Volunteerism(mainArticleState) }
        composable(navItems[11]) { Stories(storiesVM.articlesSF.collectAsState().value, storiesVM::getStoryArticles) }
    }
    var isLoading by remember { mutableStateOf(false) }
    LaunchedEffect(mainArticleState.status) {
        mainVM.articlesSF.collectLatest { state ->
            state.status.displayMessage(snackbarHostState) { isLoading = it }
        }
    }
    if(isLoading) ANTProgressIndicator(modifier)
}
@Composable
private fun ANTProgressIndicator(modifier: Modifier) {
    Box(modifier) { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
}