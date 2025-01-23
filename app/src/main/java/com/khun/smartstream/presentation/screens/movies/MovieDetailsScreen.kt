package com.khun.smartstream.presentation.screens.movies

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import com.khun.smartstream.R
import com.khun.smartstream.data.models.Detail
import com.khun.smartstream.data.util.StringConstants
import com.khun.smartstream.domain.mapper.Casts
import com.khun.smartstream.domain.mapper.Crews
import com.khun.smartstream.domain.mapper.Movie
import com.khun.smartstream.domain.mapper.RatingReviews
import com.khun.smartstream.domain.mapper.SimilarMovies
import com.khun.smartstream.presentation.common.ErrorView
import com.khun.smartstream.presentation.common.LoadingIndicator
import com.khun.smartstream.presentation.common.MoviesRow
import com.khun.smartstream.presentation.screens.dashboard.rememberChildPadding

object MovieDetailsScreen {
    const val MovieIdBundleKey = "movieId"
}

@Composable
fun MovieDetailsScreen(
    goToTrailerPlayer: (movieId: Int)-> Unit,
    goToMoviePlayer: () -> Unit,
    onBackPressed: () -> Unit,
    refreshScreenWithNewMovie: (Movie) -> Unit,
    movieDetailsScreenViewModel: MovieDetailsScreenViewModel = hiltViewModel()
) {
    val uiState by movieDetailsScreenViewModel.uiState.collectAsStateWithLifecycle()

    when (val s = uiState) {
        is MovieDetailsScreenUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingIndicator()
            }
        }

        is MovieDetailsScreenUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ErrorView(s.message)
            }
        }

        is MovieDetailsScreenUiState.Done -> {
            Details(
                movieDetails = s.movieDetails,
                similarMovies = s.similarMovies,
                ratingAndReviews = s.ratingAndReview,
                casts = s.casts,
                crews = s.crews,
                goToTrailerPlayer = goToTrailerPlayer,
                goToMoviePlayer = goToMoviePlayer,
                onBackPressed = onBackPressed,
                refreshScreenWithNewMovie = refreshScreenWithNewMovie,
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize()
            )
        }
    }
}

@Composable
private fun Details(
    movieDetails: Detail,
    similarMovies: SimilarMovies,
    ratingAndReviews: RatingReviews,
    crews: Crews,
    casts: Casts,
    goToTrailerPlayer: (movieId: Int) -> Unit,
    goToMoviePlayer: () -> Unit,
    onBackPressed: () -> Unit,
    refreshScreenWithNewMovie: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    val childPadding = rememberChildPadding()

    BackHandler(onBack = onBackPressed)
    LazyColumn(
        contentPadding = PaddingValues(bottom = 135.dp),
        modifier = modifier,
    ) {
        item {
            MovieDetails(
                movieDetails = movieDetails,
                crews = crews,
                goToTrailerMoviePlayer = goToTrailerPlayer,
                goToMoviePlayer = goToMoviePlayer
            )
        }

        item {
            CastAndCrewList(
                casts = casts,
                crews = crews,
            )
        }

        item {
            MoviesRow(
                title = StringConstants
                    .Composable
                    .movieDetailsScreenSimilarTo(movieDetails.title ?: ""),
                titleStyle = MaterialTheme.typography.titleMedium,
                movieList = similarMovies,
                onMovieSelected = refreshScreenWithNewMovie
            )
        }

        item {
            MovieReviews(
                modifier = Modifier.padding(top = childPadding.top),
                reviewsAndRatings = ratingAndReviews
            )
        }

        item {
            Box(
                modifier = Modifier
                    .padding(horizontal = childPadding.start)
                    .padding(BottomDividerPadding)
                    .fillMaxWidth()
                    .height(1.dp)
                    .alpha(0.15f)
                    .background(MaterialTheme.colorScheme.onSurface)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = childPadding.start),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val itemModifier = Modifier.width(192.dp)

                TitleValueText(
                    modifier = itemModifier,
                    title = stringResource(R.string.status),
                    value = movieDetails.status ?: ""
                )
                TitleValueText(
                    modifier = itemModifier,
                    title = stringResource(R.string.original_language),
                    value = movieDetails.original_language ?: ""
                )
                TitleValueText(
                    modifier = itemModifier,
                    title = stringResource(R.string.budget),
                    value = (movieDetails.budget ?: 0.0).toString()
                )
                TitleValueText(
                    modifier = itemModifier,
                    title = stringResource(R.string.revenue),
                    value = (movieDetails.revenue ?: 0.0).toString()
                )
            }
        }
    }
}

private val BottomDividerPadding = PaddingValues(vertical = 48.dp)
