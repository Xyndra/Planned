package de.xyndra.planned.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.xyndra.planned.*

@Composable
fun HomeScreen() {
    val data = DataStore.getOrElse<TODOList>("todos", emptyTODOList())
    Surface(modifier = Modifier.fillMaxSize()) {
        Greeting("Sammy")
        LazyColumn {
            items(data.size()) { index ->
                Text(text = data[index].title)
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