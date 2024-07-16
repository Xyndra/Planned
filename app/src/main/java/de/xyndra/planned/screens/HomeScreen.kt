package de.xyndra.planned.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.xyndra.planned.*

@Composable
fun HomeScreen(navController: NavController) {
    val data = DataStore.getOrElse("todos", emptyTODOList())
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(70.dp),
                onClick = {
                    Log.v("HomeScreen", "FloatingActionButton clicked")
                    navController.navigate("chooseNew")
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add new TODO", modifier = Modifier.size(40.dp))
            }
        }
    ) { padding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            Greeting("Sammy")
            LazyColumn {
                items(data.size()) { index ->
                    Text(text = data[index].title)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}