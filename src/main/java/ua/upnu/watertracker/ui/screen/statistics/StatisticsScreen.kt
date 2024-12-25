package ua.upnu.watertracker.ui.screen.statistics


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import ua.upnu.watertracker.R
import ua.upnu.watertracker.ui.navigation.Screen
@Composable
fun StatisticsScreen(navController: NavHostController) {

    }
    @Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Home.route) },
            label = { Text("Головна") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Головна") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Settings.route) },
            label = { Text("Налаштування") },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Налаштування") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Statistics.route) },
            label = { Text("Статистика") },
            icon = { Icon(painter = painterResource(id = R.drawable.statistics), contentDescription = "Статистика") }
        )
    }
}
