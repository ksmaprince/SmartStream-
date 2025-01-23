package com.khun.smartstream.presentation.screens.categories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.Movies
import com.khun.smartstream.domain.usecase.IGetMoviesByCategoryUseCase
import com.khun.smartstream.utils.generes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CategoryMovieListScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMoviesByCategoryUseCase: IGetMoviesByCategoryUseCase
) : ViewModel() {

    val uiState =
        savedStateHandle.getStateFlow<String?>(
            CategoryMovieListScreen.CategoryIdBundleKey,
            null
        ).flatMapLatest { id ->
            if (id == null) {
                flowOf(CategoryMovieListScreenUiState.Error("No Passing data"))
            } else {
                loadMovieByCategory(id = id)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CategoryMovieListScreenUiState.Loading
        )

    private fun loadMovieByCategory(id: String): Flow<CategoryMovieListScreenUiState> = flow {
        getMoviesByCategoryUseCase.invoke(genreId = id.toInt()).collect {
            when (it) {
                is NetworkResult.Loading -> emit(CategoryMovieListScreenUiState.Loading)
                is NetworkResult.Error -> emit(CategoryMovieListScreenUiState.Error(it.message.toString()))
                is NetworkResult.Success -> {
                    if (it.data?.results != null) {
                        val genreName = generes?.find { it.id == id.toInt() }?.name ?: "Drama"
                        emit(CategoryMovieListScreenUiState.Done(genreName, it.data.results))
                    } else
                        emit(CategoryMovieListScreenUiState.Error("No data"))
                }
            }
        }
    }
}

sealed interface CategoryMovieListScreenUiState {
    data object Loading : CategoryMovieListScreenUiState
    data class Error(val message: String) : CategoryMovieListScreenUiState
    data class Done(val categoryName: String, val movies: Movies) : CategoryMovieListScreenUiState
}
