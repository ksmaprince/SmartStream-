package com.khun.smartstream.presentation.screens.videoPlayer

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.Videos
import com.khun.smartstream.domain.usecase.IGetVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TrailerVideoPlayerScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVideoUseCase: IGetVideoUseCase,
) : ViewModel() {
    val uiState = savedStateHandle
        .getStateFlow<String?>(TrailerVideoPlayerScreen.MovieIdBundleKey, null)
        .flatMapLatest { id ->
            if (id == null) {
                flowOf(TrailerVideoPlayerScreenUiState.Error("No ID passed"))
            } else {
                loadVideos(id = id)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrailerVideoPlayerScreenUiState.Loading
        )

    private fun loadVideos(id: String): Flow<TrailerVideoPlayerScreenUiState> = flow {
        getVideoUseCase.invoke(id = id.toInt()).collect {
            when (it) {
                is NetworkResult.Loading -> emit(TrailerVideoPlayerScreenUiState.Loading)
                is NetworkResult.Error -> emit(TrailerVideoPlayerScreenUiState.Error(it.message.toString()))
                is NetworkResult.Success -> {
                    if (it.data?.results != null)
                        emit(TrailerVideoPlayerScreenUiState.Done(it.data.results))
                    else
                        emit(TrailerVideoPlayerScreenUiState.Error("No data"))
                }
            }
        }
    }
}

@Immutable
sealed class TrailerVideoPlayerScreenUiState {
    data object Loading : TrailerVideoPlayerScreenUiState()
    class Error(message: String) : TrailerVideoPlayerScreenUiState()
    data class Done(val videos: Videos) : TrailerVideoPlayerScreenUiState()
}