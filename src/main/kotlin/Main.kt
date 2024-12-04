import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.dataModule
import di.presentationModule
import org.koin.compose.KoinApplication
import presentation.navigation.MainEntryPoint

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainEntryPoint()
    }
}

fun main() = application {
    KoinApplication(
        application = {
            printLogger()
            modules(dataModule, presentationModule)
        }
    ) {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}