package presentation.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.toUri
import domain.models.Article

/**
 *   Composable function that displays list of images
 *   @param article object of [Article]. Contains list of images
 *   @param modifier
 */
@Composable
internal fun ImageSlider(
    article: Article,
    modifier: Modifier = Modifier
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    var url by remember { mutableStateOf("") }
    LazyRow(modifier, horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        items(article.content, key = { it }) { photo ->
            AsyncImage(
                model = photo.toUri(),
                contentDescription = article.title,
                Modifier.fillMaxHeight().fillMaxWidth().clickable {
                    url = photo
                    isDialogVisible = true
                }
            )
        }
    }
    if(isDialogVisible) Dialog(onDismissRequest = { isDialogVisible = false }) {
        Column(Modifier.fillMaxSize()) {
            AsyncImage(
                model = url.toUri(),
                contentDescription = article.title,
                Modifier.fillMaxSize().clickable { isDialogVisible = false },
                contentScale = ContentScale.FillWidth
            )
        }
    }
}