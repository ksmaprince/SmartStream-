package com.khun.smartstream.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.mapper.Categories
import com.khun.smartstream.domain.usecase.IGetCategoriesUseCase
import com.khun.smartstream.utils.generes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(
    getCategoriesUseCase: IGetCategoriesUseCase
) : ViewModel() {
    val uiState = getCategoriesUseCase.invoke().map {
        when (it) {
            is NetworkResult.Loading -> CategoriesScreenUiState.Loading
            is NetworkResult.Success -> {
                    generes = it.data?.genres?: listOf()
                    CategoriesScreenUiState.Ready(it.data?.genres ?: listOf())
            }

            is NetworkResult.Error -> CategoriesScreenUiState.Error
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CategoriesScreenUiState.Loading
    )
}

sealed interface CategoriesScreenUiState {
    data object Loading : CategoriesScreenUiState
    data object Error : CategoriesScreenUiState
    data class Ready(val categoryList: Categories) : CategoriesScreenUiState
}
