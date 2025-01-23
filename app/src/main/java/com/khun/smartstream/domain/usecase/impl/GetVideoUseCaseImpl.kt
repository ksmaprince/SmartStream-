package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.Video
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetVideoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetVideoUseCaseImpl(private val movieRepository: IMovieRepository) : IGetVideoUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<Video>> =
        flow {
            movieRepository.getVideo(id = id).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}