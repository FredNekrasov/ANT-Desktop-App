package presentation.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.toUri
import data.ArticleDto

/**
 *   Composable function that displays list of images
 *   @param article object of [ArticleDto]. Contains list of images
 *   @param modifier
 *
 *   When user clicks on one of the images, full screen dialog with that image is shown
 *   In the dialog, clicking on the image will close the dialog
 */
@Composable
fun ImageSlider(
    article: ArticleDto,
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
                }.padding(start = 2.dp, end = 2.dp),
            )
        }
    }
    if(isDialogVisible) Dialog(onDismissRequest = { isDialogVisible = false }) {
        Column(Modifier.clickable { isDialogVisible = false }) {
            AsyncImage(
                model = url.toUri(),
                contentDescription = article.title,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}