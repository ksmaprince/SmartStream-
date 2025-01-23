package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.TopRated
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetTopRatedUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopRatedUseCaseImpl(private val movieRepository: IMovieRepository) : IGetTopRatedUseCase {
    override fun invoke(page: Int): Flow<NetworkResult<TopRated>> =
        flow {
            movieRepository.getTopRatedMovies(page = page).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }

}