package ua.upnu.watertracker.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import ua.upnu.watertracker.R
import ua.upnu.watertracker.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    var dailyGoal by remember { mutableStateOf(2.0f) }
    var goalInGlasses by remember { mutableStateOf(false) }
    var reminderFrequency by remember { mutableStateOf(1) }

    // Using stable Material3 components
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Налаштування") }
            )
        },
        bottomBar = {
            ua.upnu.watertracker.ui.screen.home.BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Щоденна мета", style = MaterialTheme.typography.titleMedium)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = !goalInGlasses,
                        onClick = { goalInGlasses = false },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Літри")
                    Spacer(modifier = Modifier.width(16.dp))
                    RadioButton(
                        selected = goalInGlasses,
                        onClick = { goalInGlasses = true },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Склянки")
                }

                TextField(
                    value = if (goalInGlasses) {
                        TextFieldValue("${(dailyGoal * 4).toInt()}")
                    } else {
                        TextFieldValue("${dailyGoal.toInt()}")
                    },
                    onValueChange = { value ->
                        val newGoal = value.text.toFloatOrNull()
                        if (newGoal != null && newGoal > 0) {
                            dailyGoal = if (goalInGlasses) newGoal / 4 else newGoal
                        }
                    },
                    label = { Text("Ціль на день") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Частота нагадувань (години)", style = MaterialTheme.typography.titleMedium)

                TextField(
                    value = reminderFrequency.toString(),
                    onValueChange = { value ->
                        val newReminderFrequency = value.toIntOrNull()
                        if (newReminderFrequency != null) {
                            reminderFrequency = when {
                                newReminderFrequency in 1..24 -> newReminderFrequency
                                newReminderFrequency < 1 -> 1
                                else -> 24
                            }
                        }
                    },
                    label = { Text("Введіть кількість годин") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Повідомлення для частоти нагадувань
                if (reminderFrequency == 1) {
                    Text("Нагадування кожну годину", style = MaterialTheme.typography.bodyMedium)
                } else {
                    Text("Нагадування кожні $reminderFrequency години", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { navController.popBackStack() }) {
                    Text("Зберегти")
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