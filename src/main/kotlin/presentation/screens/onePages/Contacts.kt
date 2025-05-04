package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import presentation.core.*
import presentation.screens.viewModels.MainArticleState

@Composable
fun Sacraments(
    state: MainArticleState,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Column(modifier.padding(8.dp), Arrangement.Center) {
        state.list.fastForEach {
            if(it.catalog.name != ANTStrings.SACRAMENTS) return@fastForEach
            ANTTitle(text = it.title, textUnit = 26.sp)
            Spacer(modifier = Modifier.height(8.dp))
            ANTText(text = it.description, textAlign = TextAlign.Center, textUnit = 24.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        state.list.fastForEach { it ->
            if(it.catalog.name != ANTStrings.CONTACTS) return@fastForEach
            ANTTitle(text = it.title, textUnit = 26.sp)
            Spacer(modifier = Modifier.height(8.dp))
            ContactsCard(contentList = it.content) { uriHandler.openUri(it) }
        }
    }
}
/**
 * A composable function that displays a row of contact buttons.
 *
 * The buttons allow users to open different applications using the provided contact information.
 * Each button corresponds to a specific contact method: Telegram, VK, and Email.
 *
 * @param contentList A list of contact information strings.
 *                     Expected order: Telegram, VK, Phone, Email.
 * @param openSomeApp A lambda function that takes a String and is used to open an app with the given contact information.
 */
@Composable
private inline fun ContactsCard(contentList: List<String>, crossinline openSomeApp: (String) -> Unit) {
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
        ANTIconButton({ openSomeApp(contentList.getNotNull(2)) }, Icons.Default.Phone)
        ANTTextButton({ openSomeApp(contentList.getNotNull(0)) }, ANTStrings.TELEGRAM)
        ANTTextButton({ openSomeApp(contentList.getNotNull(1)) }, ANTStrings.VK)
        ANTIconButton({ openSomeApp(contentList.getNotNull(3)) }, Icons.Default.MailOutline)
    }
}
private fun List<String>.getNotNull(index: Int): String = this.getOrNull(index) ?: ""