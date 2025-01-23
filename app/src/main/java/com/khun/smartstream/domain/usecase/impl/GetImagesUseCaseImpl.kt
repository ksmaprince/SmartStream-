package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.models.Image
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetImagesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetImagesUseCaseImpl(private val movieRepository: IMovieRepository) : IGetImagesUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<Image>> =
        flow {
            movieRepository.getImages(id = id).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}