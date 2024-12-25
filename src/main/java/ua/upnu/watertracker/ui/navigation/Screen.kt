package ua.upnu.watertracker.ui.navigation

sealed  class Screen(val route: String){
    object Home: Screen("home")
    object Settings: Screen("settings")
    object Statistics: Screen("statistics")
}