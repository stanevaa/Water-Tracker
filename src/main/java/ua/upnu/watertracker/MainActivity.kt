package ua.upnu.watertracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ua.upnu.watertracker.ui.navigation.NavSetup
import ua.upnu.watertracker.ui.screen.splash.SplashViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = SplashViewModel()

        installSplashScreen().setKeepOnScreenCondition{ viewModel.isLoading.value}

        setContent {
            NavSetup()
        }
    }
}
