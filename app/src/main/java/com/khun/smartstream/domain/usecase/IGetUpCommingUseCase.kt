package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.UpComming
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetUpCommingUseCase {
    fun invoke(page: Int): Flow<NetworkResult<UpComming>>
}