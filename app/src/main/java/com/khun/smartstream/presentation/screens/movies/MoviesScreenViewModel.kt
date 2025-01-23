package com.khun.smartstream.presentation.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.models.TrendingMovie
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.Movies
import com.khun.smartstream.domain.usecase.IGetTrendingMovieUseCase
import com.khun.smartstream.utils.TimeWindow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesScreenViewModel @Inject constructor(
    getTrendingMoviesUseCase: IGetTrendingMovieUseCase
) : ViewModel() {

    val uiState = combine(
        getTrendingMoviesUseCase.invoke(TimeWindow.DAY),
        getTrendingMoviesUseCase.invoke(TimeWindow.WEEK),
    ) { (todayTranding, thisWeekTranding) ->
        processResults(todayTranding, thisWeekTranding)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MoviesScreenUiState.Loading
    )
}

private fun processResults(
    todayTranding: NetworkResult<TrendingMovie>,
    thisWeekTranding: NetworkResult<TrendingMovie>,
): MoviesScreenUiState {
    return when {
        listOf(
            todayTranding,
            thisWeekTranding
        ).any { it is NetworkResult.Loading } ->
            MoviesScreenUiState.Loading

        listOf(
            todayTranding,
            thisWeekTranding
        ).any { it is NetworkResult.Error } -> {
            MoviesScreenUiState.Error("Error in loading data")
        }

        else -> {
            val trandingMoviesToday = (todayTranding as? NetworkResult.Success)?.data?.results
            val trandingMoviesThisWeek = (thisWeekTranding as? NetworkResult.Success)?.data?.results
            if (trandingMoviesToday != null && trandingMoviesThisWeek != null) {
                MoviesScreenUiState.Ready(
                    trandingMoviesToday, trandingMoviesThisWeek
                )
            } else {
                MoviesScreenUiState.Error("Error in data loading")
            }

        }
    }
}

sealed interface MoviesScreenUiState {
    data object Loading : MoviesScreenUiState
    data class Error(val message: String) : MoviesScreenUiState
    data class Ready(
        val trandingMoviesToday: Movies,
        val trandingMoviesThisWeek: Movies
    ) : MoviesScreenUiState
}
