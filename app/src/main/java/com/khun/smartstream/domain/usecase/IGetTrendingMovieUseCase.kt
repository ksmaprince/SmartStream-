package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.model.TrendingMovie
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.utils.MediaType
import com.khun.smartstream.utils.TimeWindow
import kotlinx.coroutines.flow.Flow

interface IGetTrendingMovieUseCase {
    fun invoke(timeWindow: TimeWindow): Flow<NetworkResult<TrendingMovie>>
}