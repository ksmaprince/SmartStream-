package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.Credit
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetCreditsUseCase {
    fun invoke(id: Int): Flow<NetworkResult<Credit>>
}