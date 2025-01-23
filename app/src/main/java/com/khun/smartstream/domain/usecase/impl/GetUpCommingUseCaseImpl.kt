package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.models.UpComming
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetUpCommingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUpCommingUseCaseImpl(private val movieRepository: IMovieRepository) :
    IGetUpCommingUseCase {
    override fun invoke(page: Int): Flow<NetworkResult<UpComming>> =
        flow {
            movieRepository.getUpCommingMovies(page = page).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }

}