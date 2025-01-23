package com.khun.smartstream.presentation.common

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.khun.smartstream.data.util.StringConstants
import com.khun.smartstream.domain.mapper.Movie
import com.khun.smartstream.domain.mapper.toNormalImageUrl

@Composable
fun PosterImage(
    isToShowPortrait: Boolean,
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    val model = if (isToShowPortrait) movie.poster_path else movie.backdrop_path
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .crossfade(true)
            .data((model ?: "").toNormalImageUrl())
            .build(),
        loading = {
            CircularProgressIndicator(modifier = Modifier.requiredSize(40.dp))
        },
        contentDescription = StringConstants.Composable.ContentDescription.moviePoster(
            movie.title ?: ""
        ),
        contentScale = ContentScale.Crop
    )
}
