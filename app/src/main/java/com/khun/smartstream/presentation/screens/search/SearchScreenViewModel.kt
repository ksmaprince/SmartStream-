package com.khun.smartstream.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.Movies
import com.khun.smartstream.domain.usecase.ISearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchMovieUseCase: ISearchMovieUseCase
) : ViewModel() {

    private val internalSearchState = MutableSharedFlow<SearchState>()

    fun query(queryString: String) {
        viewModelScope.launch { postQuery(queryString) }
    }

    private suspend fun postQuery(queryString: String) {
        searchMovieUseCase.invoke(query = queryString).collect {
            when (it) {
                is NetworkResult.Loading -> internalSearchState.emit(SearchState.Searching)
                is NetworkResult.Success -> internalSearchState.emit(
                    SearchState.Done(
                        it.data?.results ?: listOf()
                    )
                )

                is NetworkResult.Error -> internalSearchState.emit(SearchState.Error)
            }
        }
    }

    val searchState = internalSearchState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchState.Done(emptyList())
    )
}

sealed interface SearchState {
    data object Searching : SearchState
    data object Error : SearchState
    data class Done(val movieList: Movies) : SearchState
}
