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
import org.koin.compose.koinInject
import org.koin.core.qualifier.qualifier
import presentation.core.*
import presentation.screens.*
import presentation.screens.onePages.*
import presentation.screens.viewModels.ArticleVM

@Composable
fun ANTNavHost(
    controller: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    articleVM: ArticleVM = koinInject(qualifier<ArticleVM>()),
    navItems: SnapshotStateList<String> = ANTStrings.screens
) {
    val state = articleVM.articlesSF.collectAsState().value
    NavHost(navController = controller, startDestination = navItems[0]) {
        composable(navItems[0]) { MainScreen(state) }
        composable(navItems[1]) { ParishLife(state, articleVM::getArticles) }
        composable(navItems[2]) { Schedule(state) }
        composable(navItems[3]) { Box(modifier) { CircularProgressIndicator(Modifier.align(Alignment.Center)) } }
        composable(navItems[4]) { YouthClub(state, articleVM::getArticles) }
        composable(navItems[5]) { Priesthood(state) }
        composable(navItems[6]) { Advices(state, articleVM::getArticles) }
        composable(navItems[7]) { History(state, articleVM::getArticles) }
        composable(navItems[8]) { Sacraments(state) }
        composable(navItems[9]) { Contacts(state) }
        composable(navItems[10]) { Box(modifier) { LinearProgressIndicator(Modifier.align(Alignment.Center)) } }
        composable(navItems[11]) { Volunteerism(state) }
        composable(navItems[12]) { Stories(state, articleVM::getArticles) }
    }
    LaunchedEffect(state.status) {
        articleVM.articlesSF.collectLatest {
            if(it.status.isError()) snackbarHostState.showSnackbar(it.status.getMessage())
        }
    }
}