package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.model.TopRated
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetTopRatedUseCase {
    fun invoke(page: Int): Flow<NetworkResult<TopRated>>
}