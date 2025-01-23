package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.TrendingTV
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetTrendingTVUseCase
import com.khun.smartstream.utils.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTrendingTVUseCaseImpl(private val movieRepository: IMovieRepository) :
    IGetTrendingTVUseCase {
    override fun invoke(
        timeWindow: TimeWindow
    ): Flow<NetworkResult<TrendingTV>> =
        flow {
            movieRepository.getTrendingTV(timeWindow = timeWindow).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}