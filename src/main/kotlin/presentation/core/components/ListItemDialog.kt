package presentation.core.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.models.Article
import presentation.core.*

/**
 * Shows the details of an [Article] with a close button.
 * The details include: type, title, date, image, and description.
 *  @param article is the article to be shown in the details
 *  @param isShowDialog is a function that is called when the close button is clicked
 */
@Composable
internal inline fun ListItemDialog(
    crossinline isShowDialog: (Boolean) -> Unit,
    article: Article
) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(MaterialTheme.colorScheme.background)) {
        FredTitle(article.articleType, textUnit = 20.sp)
        Spacer(Modifier.height(8.dp))
        FredTitle(article.title, textUnit = 20.sp)
        if(article.date.isNotBlank()) FredText(article.date, modifier = Modifier.fillMaxWidth().padding(end = 8.dp).align(Alignment.End), textUnit = 20.sp)
        Spacer(Modifier.height(4.dp))
        if(article.content.isNotEmpty()) ImageSlider(article = article, Modifier.fillMaxWidth().aspectRatio(3.0f))
        Spacer(Modifier.height(8.dp))
        FredText(article.description, textUnit = 20.sp)
        Box(Modifier.fillMaxWidth().wrapContentHeight()) {
            FredIconButton(
                onClick = { isShowDialog(false) }, icon = Icons.Default.Close,
                modifier = Modifier.align(Alignment.BottomCenter).border(2.dp, MaterialTheme.colorScheme.error, MaterialTheme.shapes.medium)
            )
        }
    }
}