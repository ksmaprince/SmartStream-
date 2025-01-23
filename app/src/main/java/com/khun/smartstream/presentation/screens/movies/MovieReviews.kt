package com.khun.smartstream.presentation.screens.movies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import androidx.tv.material3.surfaceColorAtElevation
import com.khun.smartstream.presentation.screens.dashboard.rememberChildPadding
import com.khun.smartstream.presentation.theme.SmartStreamCardShape
import com.khun.smartstream.R
import com.khun.smartstream.data.util.StringConstants
import com.khun.smartstream.domain.mapper.RatingReview
import com.khun.smartstream.domain.mapper.RatingReviews
import com.khun.smartstream.domain.mapper.toSingleDecimalString
import com.khun.smartstream.domain.mapper.truncateWithEllipsis

@Composable
fun MovieReviews(
    modifier: Modifier = Modifier,
    reviewsAndRatings: RatingReviews
) {
    val childPadding = rememberChildPadding()
    Column(
        modifier = modifier
            .padding(horizontal = childPadding.start)
            .padding(bottom = childPadding.bottom)
    ) {
        Text(text = stringResource(R.string.reviews), style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            reviewsAndRatings.take(4).forEach { reviewAndRating ->
                Review(
                    reviewAndRating,
                    modifier
                        .weight(1f)
                        .height(96.dp)
                )
            }
        }
    }
}

@Composable
private fun Review(
    reviewAndRating: RatingReview,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = {},
        tonalElevation = 1.dp,
        modifier = modifier,
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1f),
        border = ClickableSurfaceDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(
                    width = ReviewItemOutlineWidth,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = SmartStreamCardShape
            )
        ),
        shape = ClickableSurfaceDefaults.shape(shape = SmartStreamCardShape),
        colors = ClickableSurfaceDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            pressedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContentColor = MaterialTheme.colorScheme.onSurface,
            pressedContentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.3f)
                        .background(
                            MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
                        ),
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(0.8f)
                            .align(Alignment.Center),
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = (reviewAndRating.author?:"").truncateWithEllipsis(10),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = StringConstants
                            .Composable
                            .reviewCount((reviewAndRating.author_details?.rating?: 0.0).toSingleDecimalString()),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.alpha(0.75f)
                    )
                }
            }
//            Text(
//                text = (reviewAndRating.author_details?.rating?: 0.0).toSingleDecimalString(),
//                style = MaterialTheme.typography.headlineLarge,
//                modifier = Modifier.padding(end = 16.dp)
//            )
        }
    }
}

private val ReviewItemOutlineWidth = 2.dp
