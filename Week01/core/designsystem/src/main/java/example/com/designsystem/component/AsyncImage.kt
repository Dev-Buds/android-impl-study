package example.com.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.designsystem.R
import timber.log.Timber

@Composable
fun WeekAsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.None,
    placeholder: Painter = weekAsyncImagePlaceholder(),
) {
    AsyncImage(
        model = url,
        placeholder = placeholder,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        onError = { Timber.e("Failure (url: $url)") },
    )
}

@Composable
private fun weekAsyncImagePlaceholder() = painterResource(R.drawable.placeholder)
