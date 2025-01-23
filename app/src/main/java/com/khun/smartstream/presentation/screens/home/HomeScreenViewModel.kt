package com.khun.smartstream.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.models.NowPlaying
import com.khun.smartstream.data.models.Popular
import com.khun.smartstream.data.models.TopRated
import com.khun.smartstream.data.models.UpComming
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.Movies
import com.khun.smartstream.domain.usecase.IGetNowPlayingUseCase
import com.khun.smartstream.domain.usecase.IGetPopularUseCase
import com.khun.smartstream.domain.usecase.IGetTopRatedUseCase
import com.khun.smartstream.domain.usecase.IGetUpCommingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreeViewModel @Inject constructor(
    getUpCommingUseCase: IGetUpCommingUseCase,
    getPopularUseCase: IGetPopularUseCase,
    getTopRatedUseCase: IGetTopRatedUseCase,
    getNowPlayingUseCase: IGetNowPlayingUseCase
) : ViewModel() {

    val uiState: StateFlow<HomeScreenUiState> = combine(
        getUpCommingUseCase.invoke(page = 1),
        getPopularUseCase.invoke(page = 1),
        getTopRatedUseCase.invoke(page = 1),
        getNowPlayingUseCase.invoke(page = 1)
    ) { featureMovies, trendingMovies, top10Movies, nowPlayingMovies ->
        processResults(
            featureMovies = featureMovies,
            trendingMovies = trendingMovies,
            top10Movies = top10Movies,
            nowPlayingMovies = nowPlayingMovies
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeScreenUiState.Loading
    )

    private fun processResults(
        featureMovies: NetworkResult<UpComming>,
        trendingMovies: NetworkResult<Popular>,
        top10Movies: NetworkResult<TopRated>,
        nowPlayingMovies: NetworkResult<NowPlaying>,
    ): HomeScreenUiState {
        return when {
            listOf(
                featureMovies,
                trendingMovies,
                top10Movies,
                nowPlayingMovies
            ).any { it is NetworkResult.Loading } ->
                HomeScreenUiState.Loading

            listOf(
                featureMovies,
                trendingMovies,
                top10Movies,
                nowPlayingMovies
            ).any { it is NetworkResult.Error } -> {
                HomeScreenUiState.Error("Error in loading data")
            }

            else -> {
                val featuresMovieList = (featureMovies as? NetworkResult.Success)?.data?.results
                val trendingMovieList = (trendingMovies as? NetworkResult.Success)?.data?.results
                val top10MovieList = (top10Movies as? NetworkResult.Success)?.data?.results
                val nowPlayingMovieList =
                    (nowPlayingMovies as? NetworkResult.Success)?.data?.results
                if (featuresMovieList != null && trendingMovieList != null && top10MovieList != null && nowPlayingMovieList != null) {
                    HomeScreenUiState.Ready(
                        featuresMovieList, trendingMovieList, top10MovieList, nowPlayingMovieList
                    )
                } else {
                    HomeScreenUiState.Error("Error in data loading")
                }

            }
        }
    }
}

sealed interface HomeScreenUiState {
    data object Loading : HomeScreenUiState
    data class Error(val message: String) : HomeScreenUiState
    data class Ready(
        val featuredMovieList: Movies,
        val trendingMovieList: Movies,
        val top10MovieList: Movies,
        val nowPlayingMovieList: Movies
    ) : HomeScreenUiState
}
