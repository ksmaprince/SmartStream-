package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.TrendingMovie
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetTrendingMovieUseCase
import com.khun.smartstream.utils.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTrendingMovieUseCaseImpl(private val movieRepository: IMovieRepository) :
    IGetTrendingMovieUseCase {
    override fun invoke(
        timeWindow: TimeWindow
    ): Flow<NetworkResult<TrendingMovie>> =
        flow {
            movieRepository.getTrendingMovies(timeWindow = timeWindow).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}