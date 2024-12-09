package presentation.screens.onePages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import presentation.core.*
import presentation.screens.viewModels.ArticleState

/**
 *   Contacts screen with buttons for phone, telegram, vk, email.
 *   @param state
 */
@Composable
fun Contacts(state: ArticleState) {
    val uriHandler = LocalUriHandler.current
    Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        state.list.forEach { it ->
            if(it.articleType != ANTStrings.CONTACTS) return@forEach
            FredTitle(it.title)
            Spacer(modifier = Modifier.height(8.dp))
            ContactsCard(contentList = it.content) { uriHandler.openUri(it) }
        }
    }
}
/**
 * Contacts card with buttons for phone, telegram, vk, email
 * @param contentList list of the contact information
 * @param openSomeApp action for opening app like telegram, vk, gmail and phone
 */
@Composable
private inline fun ContactsCard(contentList: List<String>, crossinline openSomeApp: (String) -> Unit) {
    Row(Modifier.fillMaxWidth().wrapContentHeight(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
        FredIconButton({ openSomeApp(contentList.getNotNull(2)) }, Icons.Default.Phone, ANTStrings.PHONE)
        FredTButton({ openSomeApp(contentList.getNotNull(0)) }, ANTStrings.TELEGRAM)
        FredTButton({ openSomeApp(contentList.getNotNull(1)) }, ANTStrings.VK)
        FredIconButton({ openSomeApp(contentList.getNotNull(3)) }, Icons.Default.MailOutline, ANTStrings.EMAIL)
    }
}