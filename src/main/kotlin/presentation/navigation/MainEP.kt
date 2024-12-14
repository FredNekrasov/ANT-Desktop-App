package presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import presentation.core.*

@Composable
fun MainEntryPoint() {
    val uriHandler = LocalUriHandler.current
    val navItems = ANTStrings.screens
    val controller = rememberNavController()
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navigateTo: (Int, String) -> Unit = { index, route ->
        controller.navigate(route)
        selectedItemIndex = index
    }
    val currentRoute = controller.currentBackStackEntryAsState().value?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = { FredTopAppBar(currentRoute != navItems[2]) { navigateTo(2, navItems[2]) } },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Row(Modifier.fillMaxSize().padding(padding)) {
            VerticalDivider()
            LazyColumn(Modifier.fillMaxHeight().fillMaxWidth(0.24f).padding(4.dp)) {
                itemsIndexed(navItems) { index, route ->
                    FredNavigationDrawerItem(
                        text = route,
                        selected = index == selectedItemIndex,
                        onClick = {
                            when(route) {
                                ANTStrings.SPIRITUAL_TALKS -> uriHandler.openUri(ANTStrings.SPIRITUAL_TALKS_URL)
                                ANTStrings.INFORMATION -> uriHandler.openUri(ANTStrings.INFORMATION_URL)
                                else -> navigateTo(index, route)
                            }
                        }
                    )
                    Spacer(Modifier.height(2.dp))
                }
            }
            VerticalDivider()
            Box(Modifier.fillMaxSize().padding(8.dp)) { ANTNavHost(controller, snackbarHostState) }
        }
    }
}