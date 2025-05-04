package presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.qualifier.qualifier
import presentation.core.*
import presentation.screens.*
import presentation.screens.onePages.*
import presentation.screens.viewModels.*

@Composable
fun ANTNavHost(
    controller: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    navItems: SnapshotStateList<String> = ANTStrings.screens
) {
    val mainVM: MainVM = koinViewModel(qualifier<MainVM>())
    val parishLifeVM: ParishLifeVM = koinViewModel(qualifier<ParishLifeVM>())
    val youthClubVM: YouthClubVM = koinViewModel(qualifier<YouthClubVM>())
    val advicesVM: AdvicesVM = koinViewModel(qualifier<AdvicesVM>())
    val historyVM: HistoryVM = koinViewModel(qualifier<HistoryVM>())
    val storiesVM: StoriesVM = koinViewModel(qualifier<StoriesVM>())
    val mainArticleState = mainVM.articlesSF.collectAsState().value
    NavHost(navController = controller, startDestination = navItems[0]) {
        composable(navItems[0]) { MainScreen(mainArticleState, modifier) }
        composable(navItems[1]) { ParishLife(parishLifeVM.collectSFValue(), parishLifeVM::getParishLifeArticles, modifier) }
        composable(navItems[2]) { Schedule(mainArticleState, modifier) }
        composable(navItems[3]) { ANTProgressIndicator(modifier) }
        composable(navItems[4]) { YouthClub(youthClubVM.collectSFValue(), youthClubVM::getYouthClubArticles, modifier) }
        composable(navItems[5]) { Priesthood(mainArticleState, modifier) }
        composable(navItems[6]) { Advices(advicesVM.collectSFValue(), advicesVM::getAdviceArticles, modifier) }
        composable(navItems[7]) { History(historyVM.collectSFValue(), historyVM::getHistoryArticles, modifier) }
        composable(navItems[8]) { Sacraments(mainArticleState, modifier) }
        composable(navItems[9]) { ANTProgressIndicator(modifier) }
        composable(navItems[10]) { Volunteerism(mainArticleState, modifier) }
        composable(navItems[11]) { Stories(storiesVM.collectSFValue(), storiesVM::getStoryArticles, modifier) }
    }
    LaunchedEffect(mainArticleState.isLoading) {
        mainVM.articlesSF.collectLatest { state ->
            if(state.hasError) snackbarHostState.showSnackbar(ANTStrings.UNKNOWN_ERROR)
        }
    }
    if(mainArticleState.isLoading) ANTProgressIndicator(modifier)
}
@Composable
private fun ANTProgressIndicator(modifier: Modifier) {
    Box(modifier) { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
}
@Composable
private fun ArticleVM.collectSFValue() = this.articlesSF.collectAsState().value