package com.example.movieapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.models.MovieDataSource

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

@Composable
fun MainContent(modifier: Modifier, movieId: String?, goBackClickListener: () -> Unit) {
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
            Text(
                text = movie.Title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(23.dp))
            Button(onClick = {
                goBackClickListener()
            }) {
                Text(text = "GO BACK")
            }
        }
    }
}