package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.models.Detail
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetDetailUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDetailUseCaseImpl(private val movieRepository: IMovieRepository) : IGetDetailUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<Detail>> =
        flow {
            movieRepository.getMovieDetail(id = id).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}

