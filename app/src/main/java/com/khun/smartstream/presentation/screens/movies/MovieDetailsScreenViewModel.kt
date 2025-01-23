package com.khun.smartstream.presentation.screens.movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.model.Credit
import com.khun.smartstream.data.model.Detail
import com.khun.smartstream.data.model.Review
import com.khun.smartstream.data.model.SimilarMovie
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.Casts
import com.khun.smartstream.domain.mapper.Crews
import com.khun.smartstream.domain.mapper.RatingReviews
import com.khun.smartstream.domain.mapper.SimilarMovies
import com.khun.smartstream.domain.usecase.IGetCreditsUseCase
import com.khun.smartstream.domain.usecase.IGetDetailUseCase
import com.khun.smartstream.domain.usecase.IGetReviewUseCase
import com.khun.smartstream.domain.usecase.IGetSimilarMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDetailUseCase: IGetDetailUseCase,
    private val getSimilarMovieUseCase: IGetSimilarMovieUseCase,
    private val getReviewUseCase: IGetReviewUseCase,
    private val getCreditUseCase: IGetCreditsUseCase
) : ViewModel() {

    val uiState = savedStateHandle
        .getStateFlow<String?>(MovieDetailsScreen.MovieIdBundleKey, null)
        .flatMapLatest { id ->
            if (id == null) {
                flowOf(MovieDetailsScreenUiState.Error("No ID passed"))
            } else {
                loadMovieDetails(id)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailsScreenUiState.Loading
        )

    private fun loadMovieDetails(id: String): Flow<MovieDetailsScreenUiState> {
        return combine(
            getDetailUseCase.invoke(id = id.toInt()),
            getSimilarMovieUseCase.invoke(id = id.toInt()),
            getReviewUseCase.invoke(id = id.toInt()),
            getCreditUseCase.invoke(id = id.toInt())
        ) { detail, similarMovie, review, credit ->
            processResults(detail, similarMovie, review, credit)
        }.catch { e ->
            emit(MovieDetailsScreenUiState.Error("Failed to load data: ${e.message}"))
        }
    }

    private fun processResults(
        detail: NetworkResult<Detail>,
        similarMovie: NetworkResult<SimilarMovie>,
        review: NetworkResult<Review>,
        credit: NetworkResult<Credit>
    ): MovieDetailsScreenUiState {
        return when {
            listOf(detail, similarMovie, review, credit).any { it is NetworkResult.Loading } -> {
                MovieDetailsScreenUiState.Loading
            }

            listOf(detail, similarMovie, review, credit).any { it is NetworkResult.Error } -> {
                val errorMessage = listOf(detail, similarMovie, review, credit)
                    .filter { it is NetworkResult.Error }
                    .joinToString("") { it.message ?: "Unknown Error" }
                MovieDetailsScreenUiState.Error("Error loading data: $errorMessage")
            }

            else -> {
                val movieDetail = (detail as? NetworkResult.Success)?.data
                val similar = (similarMovie as? NetworkResult.Success)?.data?.results
                val reviews = (review as? NetworkResult.Success)?.data?.results
                val crews = (credit as NetworkResult.Success).data?.crew
                val casts = (credit as NetworkResult.Success).data?.cast

                if (movieDetail != null && similar != null && reviews != null && crews != null && casts != null) {
                    MovieDetailsScreenUiState.Done(movieDetail, similar, reviews, crews, casts)
                } else {
                    MovieDetailsScreenUiState.Error("Incomplete data received")
                }
            }
        }
    }
}

sealed class MovieDetailsScreenUiState {
    object Loading : MovieDetailsScreenUiState()
    data class Error(val message: String) : MovieDetailsScreenUiState()
    data class Done(
        val movieDetails: Detail,
        val similarMovies: SimilarMovies,
        val ratingAndReview: RatingReviews,
        val crews: Crews,
        val casts: Casts
    ) : MovieDetailsScreenUiState()
}
