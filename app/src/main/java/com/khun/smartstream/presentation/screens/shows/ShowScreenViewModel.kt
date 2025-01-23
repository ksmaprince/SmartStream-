package com.khun.smartstream.presentation.screens.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.models.TrendingTV
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.TVs
import com.khun.smartstream.domain.usecase.IGetTrendingTVUseCase
import com.khun.smartstream.utils.TimeWindow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ShowScreenViewModel @Inject constructor(
    getTrendingTVUseCase: IGetTrendingTVUseCase
) : ViewModel() {

    val uiState = combine(
        getTrendingTVUseCase.invoke(TimeWindow.DAY),
        getTrendingTVUseCase.invoke(TimeWindow.WEEK),
    ) { (todayTV, thisWeekTV) ->
        processResults(todayTV, thisWeekTV)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ShowScreenUiState.Loading
    )

    private fun processResults(
        todayTV: NetworkResult<TrendingTV>,
        thisWeekTV: NetworkResult<TrendingTV>,
    ): ShowScreenUiState {
        return when {
            listOf(
                todayTV,
                thisWeekTV
            ).any { it is NetworkResult.Loading } ->
                ShowScreenUiState.Loading

            listOf(
                todayTV,
                thisWeekTV
            ).any { it is NetworkResult.Error } -> {
                ShowScreenUiState.Error("${todayTV.message} - ${thisWeekTV.message}")
            }

            else -> {
                val tvTrendingTodays = (todayTV as? NetworkResult.Success)?.data?.results
                val tvTrendingThisWeeks = (thisWeekTV as? NetworkResult.Success)?.data?.results
                if (tvTrendingTodays != null && tvTrendingThisWeeks != null) {
                    ShowScreenUiState.Ready(
                        tvTrendingTodays, tvTrendingThisWeeks
                    )
                } else {
                    ShowScreenUiState.Error("Error in data loading x x x")
                }

            }
        }
    }
}

sealed interface ShowScreenUiState {
    data object Loading : ShowScreenUiState
    data class Error(val message: String) : ShowScreenUiState
    data class Ready(
        val tvTrendingTodays: TVs,
        val tvTrendingThisWeeks: TVs
    ) : ShowScreenUiState
}
