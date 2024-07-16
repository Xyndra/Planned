package de.xyndra.planned.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

enum class Possibilities {
    Basic,
}

@Composable
fun ChooseNewScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Choose a new item to add:")

                for (possibility in Possibilities.entries) {
                    Button(
                        shape = RoundedCornerShape(20),
                        onClick = { navigateToNextScreen(navController, possibility) }
                    ) {
                        Text(possibility.name)
                    }
                }
            }
        }
    }
}

fun navigateToNextScreen(navController: NavHostController, possibility: Possibilities) {
    val route = when (possibility) {
        Possibilities.Basic -> "newBasic"
    }
    navController.navigate(route)
}