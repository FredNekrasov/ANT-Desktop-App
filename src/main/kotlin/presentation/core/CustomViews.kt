package presentation.core

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.toUri

typealias Action = () -> Unit
@Composable
fun ANTText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Justify, textUnit: TextUnit = 20.sp) {
    Text(
        text,
        modifier,
        fontSize = textUnit,
        fontFamily = FontFamily.Serif,
        textAlign = textAlign
    )
}
@Composable
fun ANTTitle(text: String, textUnit: TextUnit = 20.sp) {
    Text(
        text,
        Modifier.fillMaxWidth(),
        fontSize = textUnit,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}
@Composable
fun ANTTextButton(onClick: Action, text: String, modifier: Modifier = Modifier) {
    TextButton(onClick, modifier) {
        ANTText(text)
    }
}
@Composable
fun ANTIconButton(onClick: Action, icon: ImageVector, modifier: Modifier = Modifier, enabled: Boolean = true) {
    IconButton(onClick, modifier, enabled) {
        Icon(icon, icon.toString())
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ANTTopAppBar(isButtonVisible: Boolean, action: Action) {
    TopAppBar(
        title = { ANTText(ANTStrings.MAIN_TITLE) },
        modifier = Modifier.fillMaxWidth().border(DividerDefaults.Thickness, MaterialTheme.colorScheme.primary),
        actions = { if(isButtonVisible) ANTIconButton(action, Icons.Outlined.DateRange) }
    )
}
@Composable
fun ANTNavigationDrawerItem(text: String, selected: Boolean, onClick: Action) {
    NavigationDrawerItem(
        label = { ANTText(text) },
        selected,
        onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = NavigationDrawerItemDefaults.colors()
    )
}
@Composable
fun ANTCard(onClick: Action, uri: String?, title: String, date: String, modifier: Modifier = Modifier) {
    Card(
        onClick,
        modifier.border(DividerDefaults.Thickness, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small).padding(4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.outlinedCardColors()
    ) {
        if(!uri.isNullOrBlank()) Box(Modifier.fillMaxHeight(0.2f).fillMaxWidth()) {
            AsyncImage(model = uri.toUri(), contentDescription = title)
        }
        Text(title, Modifier.align(Alignment.CenterHorizontally), fontFamily = FontFamily.Serif, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis, maxLines = 1)
        ANTText(date, modifier = Modifier.align(Alignment.End))
    }
}