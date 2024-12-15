import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.*
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import di.dataModule
import di.presentationModule
import okhttp3.OkHttpClient
import org.koin.compose.KoinApplication
import presentation.navigation.MainEntryPoint

@Composable
@Preview
fun App() {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context).components {
                add(OkHttpNetworkFetcherFactory(OkHttpClient()))
            }.crossfade(true).logger(DebugLogger()).build()
        }
        MainEntryPoint()
    }
}

fun main() = application {
    val state = rememberWindowState(placement = WindowPlacement.Maximized)
    KoinApplication(
        application = {
            printLogger()
            modules(dataModule, presentationModule)
        }
    ) {
        Window(onCloseRequest = ::exitApplication, state = state) {
            App()
        }
    }
}