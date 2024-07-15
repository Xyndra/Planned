package de.xyndra.planned

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.xyndra.planned.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home.toString()) {
        composable(Home.toString()) {
            HomeScreen()
        }
    }
}