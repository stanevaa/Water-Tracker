package ua.upnu.watertracker.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.res.painterResource
import ua.upnu.watertracker.R
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ua.upnu.watertracker.ui.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var currentWater by remember { mutableStateOf(0) }
    val dailyGoal = 2000
    val percentage = (currentWater / dailyGoal.toFloat()) * 100
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Головна") }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Щоденна мета: $dailyGoal мл",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = currentWater / dailyGoal.toFloat(),
                        modifier = Modifier.size(150.dp),
                        strokeWidth = 8.dp
                    )
                    Text(
                        text = "${percentage.toInt()}%",
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Випито: $currentWater мл",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { currentWater += 250 }) {
                    Text(text = "Додати порцію (250 мл)")
                }
            }
        }
    )
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
