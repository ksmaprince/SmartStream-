package com.khun.smartstream.presentation.screens.shows

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.khun.smartstream.data.util.StringConstants
import com.khun.smartstream.domain.mapper.TV
import com.khun.smartstream.domain.mapper.TVs
import com.khun.smartstream.domain.mapper.toMovie
import com.khun.smartstream.domain.mapper.toTV
import com.khun.smartstream.presentation.common.ErrorView
import com.khun.smartstream.presentation.common.LoadingIndicator
import com.khun.smartstream.presentation.common.MoviesRow
import com.khun.smartstream.presentation.screens.dashboard.rememberChildPadding
import com.khun.smartstream.presentation.screens.movies.MoviesScreenMovieList

@Composable
fun ShowsScreen(
    onTVShowClick: (tv: TV) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
    isTopBarVisible: Boolean,
    showScreenViewModel: ShowScreenViewModel = hiltViewModel(),
) {
    val uiState = showScreenViewModel.uiState.collectAsStateWithLifecycle()
    when (val currentState = uiState.value) {
        is ShowScreenUiState.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }

        is ShowScreenUiState.Ready -> {
            Catalog(
                todayTVs = currentState.tvTrendingTodays,
                thisWeekTVs = currentState.tvTrendingThisWeeks,
                onTVShowClick = onTVShowClick,
                onScroll = onScroll,
                isTopBarVisible = isTopBarVisible,
                modifier = Modifier.fillMaxSize()
            )
        }

        is ShowScreenUiState.Error -> {
            ErrorView(currentState.message)
        }
    }
}

@Composable
private fun Catalog(
    todayTVs: TVs,
    thisWeekTVs: TVs,
    onTVShowClick: (TV) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
    isTopBarVisible: Boolean,
    modifier: Modifier = Modifier
) {
    val childPadding = rememberChildPadding()
    val lazyListState = rememberLazyListState()
    val shouldShowTopBar by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == 0 &&
                    lazyListState.firstVisibleItemScrollOffset == 0
        }
    }

    LaunchedEffect(shouldShowTopBar) {
        onScroll(shouldShowTopBar)
    }
    LaunchedEffect(isTopBarVisible) {
        if (isTopBarVisible) lazyListState.animateScrollToItem(0)
    }

    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        contentPadding = PaddingValues(top = childPadding.top, bottom = 104.dp)
    ) {
        item {
            MoviesScreenMovieList(
                movieList = todayTVs.map { it.toMovie() },
                onMovieClick = { movie ->
                    onTVShowClick(movie.toTV())
                }
            )
        }
        item {
            MoviesRow(
                modifier = Modifier.padding(top = childPadding.top),
                title = StringConstants.Composable.BingeWatchDramasTitle,
                movieList = thisWeekTVs.map { it.toMovie() },
                onMovieSelected = { movie ->
                    onTVShowClick(movie.toTV())
                }
            )
        }
    }
}
