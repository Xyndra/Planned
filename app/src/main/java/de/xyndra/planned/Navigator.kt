package de.xyndra.planned

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.xyndra.planned.screens.ChooseNewScreen
import de.xyndra.planned.screens.HomeScreen
import de.xyndra.planned.screens.NewBasicScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home",
        enterTransition = { this.slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        exitTransition = { this.slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        popEnterTransition = { this.slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
        popExitTransition = { this.slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("chooseNew") {
            ChooseNewScreen(navController)
        }
        composable("newBasic") {
            NewBasicScreen(navController)
        }
    }
}