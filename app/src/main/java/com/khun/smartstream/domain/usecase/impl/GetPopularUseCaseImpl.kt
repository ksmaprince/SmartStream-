package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.models.Popular
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetPopularUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularUseCaseImpl(private val movieRepository: IMovieRepository) : IGetPopularUseCase {
    override fun invoke(page: Int): Flow<NetworkResult<Popular>> =
        flow {
            movieRepository.getPopular(page = page).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }

}