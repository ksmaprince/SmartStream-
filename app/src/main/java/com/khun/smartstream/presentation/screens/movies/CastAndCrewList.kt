package com.khun.smartstream.presentation.screens.movies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ClassicCard
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.khun.smartstream.R
import com.khun.smartstream.data.models.Cast
import com.khun.smartstream.data.models.Crew
import com.khun.smartstream.data.util.StringConstants
import com.khun.smartstream.domain.mapper.Casts
import com.khun.smartstream.domain.mapper.Crews
import com.khun.smartstream.domain.mapper.toNormalImageUrl
import com.khun.smartstream.presentation.screens.dashboard.rememberChildPadding
import com.khun.smartstream.presentation.theme.SmartStreamBorderWidth
import com.khun.smartstream.presentation.theme.SmartStreamCardShape
import com.khun.smartstream.presentation.utils.ourColors

@Composable
fun CastAndCrewList(
    crews: Crews,
    casts: Casts
) {
    val childPadding = rememberChildPadding()

    Column(
        modifier = Modifier.padding(top = childPadding.top),
    ) {
        Text(
            text = stringResource(R.string.casts),
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(start = childPadding.start)
        )
        // ToDo: specify the pivot offset
        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp)
                .focusRestorer(),
            contentPadding = PaddingValues(start = childPadding.start)
        ) {
            items(casts, key = { it.id.toString() }) {
                CastItem(it, modifier = Modifier.width(144.dp))
            }
        }
        Text(
            text = stringResource(R.string.crews),
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(start = childPadding.start)
        )
        // ToDo: specify the pivot offset
        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp)
                .focusRestorer(),
            contentPadding = PaddingValues(start = childPadding.start)
        ) {
            items(crews, key = { it.id.toString() }) {
                CrewItem(it, modifier = Modifier.width(144.dp))
            }
        }
    }
}

@Composable
private fun CastItem(
    cast: Cast,
    modifier: Modifier = Modifier,
) {
    ClassicCard(
        modifier = modifier
            .padding(end = 20.dp, bottom = 16.dp)
            .aspectRatio(1 / 1.8f),
        shape = CardDefaults.shape(shape = SmartStreamCardShape),
        scale = CardDefaults.scale(focusedScale = 1f),
        border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(
                    width = SmartStreamBorderWidth,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = SmartStreamCardShape
            )
        ),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 12.dp),
                text = cast.original_name ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis
            )
        },
        subtitle = {
            Text(
                text = cast.character ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .alpha(0.75f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                overflow = TextOverflow.Ellipsis
            )
        },
        image = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.725f)
                    .background(ourColors.random())
            ) {
                SubcomposeAsyncImage(
                    modifier = modifier,
                    model = ImageRequest.Builder(LocalContext.current)
                        .crossfade(true)
                        .data((cast.profile_path ?: "").toNormalImageUrl())
                        .build(),
                    loading = {
                        CircularProgressIndicator(modifier = Modifier.requiredSize(40.dp))
                    },
                    contentDescription = StringConstants.Composable.ContentDescription.moviePoster("Cast Poster"),
                    contentScale = ContentScale.Crop
                )
            }
        },
        onClick = {}
    )
}

@Composable
private fun CrewItem(
    crew: Crew,
    modifier: Modifier = Modifier,
) {
    ClassicCard(
        modifier = modifier
            .padding(end = 20.dp, bottom = 16.dp)
            .aspectRatio(1 / 1.8f),
        shape = CardDefaults.shape(shape = SmartStreamCardShape),
        scale = CardDefaults.scale(focusedScale = 1f),
        border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(
                    width = SmartStreamBorderWidth,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = SmartStreamCardShape
            )
        ),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 12.dp),
                text = crew.original_name ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis
            )
        },
        subtitle = {
            Text(
                text = crew.job ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .alpha(0.75f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                overflow = TextOverflow.Ellipsis
            )
        },
        image = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.725f)
                    .background(ourColors.random())
            ) {
                SubcomposeAsyncImage(
                    modifier = modifier,
                    model = ImageRequest.Builder(LocalContext.current)
                        .crossfade(true)
                        .data((crew.profile_path ?: "").toNormalImageUrl())
                        .build(),
                    loading = {
                        CircularProgressIndicator(modifier = Modifier.requiredSize(40.dp))
                    },
                    contentDescription = StringConstants.Composable.ContentDescription.moviePoster("Cast Poster"),
                    contentScale = ContentScale.Crop
                )
            }
        },
        onClick = {}
    )
}
