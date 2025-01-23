package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.TrendingMovie
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.utils.TimeWindow
import kotlinx.coroutines.flow.Flow

interface IGetTrendingMovieUseCase {
    fun invoke(timeWindow: TimeWindow): Flow<NetworkResult<TrendingMovie>>
}