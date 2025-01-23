package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.model.Popular
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetPopularUseCase {
    fun invoke(page: Int): Flow<NetworkResult<Popular>>
}