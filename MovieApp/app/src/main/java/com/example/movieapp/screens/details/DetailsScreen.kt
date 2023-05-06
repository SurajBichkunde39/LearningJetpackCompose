package com.example.movieapp.screens.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.models.MovieDataSource
import com.example.movieapp.screens.home.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Movies Details")
            }, navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back",
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
            })
        },
    ) { contentPadding ->
        MainContent(modifier = Modifier.padding(contentPadding), movieId) {
            navController.popBackStack()
        }
    }
}

@Preview
@Composable
fun MainContent(modifier: Modifier = Modifier, movieId: String? = MovieDataSource.movies[0].imdbID, goBackClickListener: () -> Unit = {}) {
     val movie = movieId?.let { MovieDataSource.getMovie(it) } ?: throw java.lang.IllegalArgumentException("Movie Id $movieId is not found is db.")
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieRow(movie)
            Text(
                text = movie.Title,
                style = MaterialTheme.typography.headlineSmall
            )

            LazyRow {
                items(movie.Images){
                    Card {
                        Log.d("Something","I am showing the image. Rest I do not know.")
                        Text("Fuck it..")
                    // Image(painter = rememberAsyncImagePainter(model = it), contentDescription = "Movie Poster")
                    }
                }
            }
        }
    }
}