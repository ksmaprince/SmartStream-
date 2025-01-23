package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.TrendingTV
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.utils.TimeWindow
import kotlinx.coroutines.flow.Flow

interface IGetTrendingTVUseCase {
    fun invoke(timeWindow: TimeWindow): Flow<NetworkResult<TrendingTV>>
}