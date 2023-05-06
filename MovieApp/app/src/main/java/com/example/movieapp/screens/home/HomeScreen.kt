package com.example.movieapp.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieDataSource
import com.example.movieapp.navigation.MovieScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Movies")
            })
        },
    ) { contentPadding ->
        val movies = MovieDataSource.movies
        MainContent(modifier = Modifier.padding(contentPadding), navController, moviesList = movies)
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    moviesList: List<Movie>
) {
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        Column {
            LazyColumn {
                items(moviesList) {
                    MovieRow(it) { movieId ->
                        navController.navigate(MovieScreens.DetailsScreen.name + "/$movieId")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieRow(
    movie: Movie = MovieDataSource.movies[0],
    onItemClicked: (String) -> Unit = { }
){
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onItemClicked(movie.imdbID) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .size(100.dp),
                shape = RectangleShape,
                tonalElevation = 4.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                    model = movie.Images[0]),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Movie image"
                )
            }
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = movie.Title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Director: ${movie.Director}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Released: ${movie.Released}", style = MaterialTheme.typography.bodySmall)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                ),
                            ){
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light
                                ),
                            ){
                                append(movie.Plot)
                            }
                        }, lineHeight = 15.sp)
                        Divider(modifier = Modifier.padding(5.dp))
                        Text("Genre: ${movie.Genre}")
                        Text("Actors: ${movie.Actors}")
                        Text("Rating: ${movie.imdbRating}")
                    }
                }
                val icon = if(expanded){
                    Icons.Filled.KeyboardArrowUp
                } else {
                    Icons.Filled.KeyboardArrowDown
                }
                Icon(
                    icon,
                    contentDescription = "Arrow Down",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded },
                    tint = Color.DarkGray
                )
            }
        }
    }
}
