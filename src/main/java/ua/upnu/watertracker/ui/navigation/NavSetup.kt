package ua.upnu.watertracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost as ComposeNavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import ua.upnu.watertracker.ui.screen.home.HomeScreen
import ua.upnu.watertracker.ui.screen.settings.SettingsScreen
import ua.upnu.watertracker.ui.screen.statistics.StatisticsScreen

@Composable
fun NavSetup() {
    val navController = rememberNavController()
    ComposeNavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        composable(Screen.Statistics.route) { StatisticsScreen(navController) }
    }
}
