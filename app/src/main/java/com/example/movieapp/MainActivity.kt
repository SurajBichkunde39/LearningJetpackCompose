package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier, content: @Composable (modifier: Modifier) -> Unit) {
    MovieAppTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(title = {
                    Text("Movies")
                })
            },
        ) { contentPadding ->
            content(modifier = Modifier.padding(contentPadding))
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    moviesList: List<String> = listOf(
        "Iron Man-1",
        "Iron Man-2",
        "Iron Man-3",
        "Iron Man-4",
        "Iron Man-5",
    )
) {
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        Column {
            LazyColumn {
                items(moviesList) {
                    MovieRow(it) {
                        Log.d("Something", "clicked Movie = $it")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(movie: String, onItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onItemClicked(movie) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                tonalElevation = 4.dp
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Movie Icon"
                )
            }
            Text(text = movie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp { modifier ->
        MainContent(modifier)
    }
}
