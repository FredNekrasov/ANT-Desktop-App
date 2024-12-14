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
import coil3.compose.AsyncImage
import coil3.toUri

@Composable
fun FredText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Justify, textUnit: TextUnit = TextUnit.Unspecified) {
    Text(
        text,
        modifier,
        fontSize = textUnit,
        fontFamily = FontFamily.Serif,
        textAlign = textAlign
    )
}
@Composable
fun FredTitle(text: String, textUnit: TextUnit = TextUnit.Unspecified) {
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
fun FredTButton(onClick: Action, text: String, modifier: Modifier = Modifier) {
    TextButton(onClick, modifier) {
        FredText(text, Modifier.wrapContentSize())
    }
}
@Composable
fun FredIconButton(onClick: Action, icon: ImageVector, description: String, modifier: Modifier = Modifier) {
    IconButton(onClick, modifier) {
        Icon(icon, description)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FredTopAppBar(isButtonVisible: Boolean, action: Action) {
    TopAppBar(
        title = { FredText(ANTStrings.MAIN_TITLE) },
        modifier = Modifier.fillMaxWidth().border(DividerDefaults.Thickness, MaterialTheme.colorScheme.primary),
        actions = { if(isButtonVisible) FredIconButton(action, Icons.Outlined.DateRange, ANTStrings.SCHEDULE) }
    )
}
@Composable
fun FredNavigationDrawerItem(text: String, selected: Boolean, onClick: Action) {
    NavigationDrawerItem(
        label = { FredText(text) },
        selected,
        onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = NavigationDrawerItemDefaults.colors()
    )
}
@Composable
fun FredCard(onClick: Action, uri: String?, title: String, date: String, modifier: Modifier = Modifier) {
    Card(
        onClick,
        modifier.border(DividerDefaults.Thickness, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium).padding(4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.outlinedCardColors()
    ) {
        if(!uri.isNullOrBlank()) AsyncImage(model = uri.toUri(), contentDescription = title, modifier = Modifier.fillMaxHeight(0.2f).fillMaxWidth(0.8f).padding(16.dp))
        Spacer(Modifier.height(4.dp))
        Text(title, Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 4.dp), fontFamily = FontFamily.Serif, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis, maxLines = 1)
        Spacer(Modifier.height(4.dp))
        FredText(date, modifier = Modifier.fillMaxWidth().padding(8.dp).align(Alignment.End))
    }
}