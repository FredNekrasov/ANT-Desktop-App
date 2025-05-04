package presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import presentation.core.ANTStrings
import presentation.core.ANTNavigationDrawerItem

@Composable
fun ANTNavDrawer(
    navigateTo: (Int, String) -> Unit,
    selectedItemIndex: Int
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(Modifier.fillMaxHeight().fillMaxWidth(0.24f).padding(4.dp)) {
        itemsIndexed(ANTStrings.screens) { index, route ->
            ANTNavigationDrawerItem(
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
}