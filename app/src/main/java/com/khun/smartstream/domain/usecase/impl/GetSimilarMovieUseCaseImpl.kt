package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.SimilarMovie
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetSimilarMovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSimilarMovieUseCaseImpl(private val movieRepository: IMovieRepository) :
    IGetSimilarMovieUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<SimilarMovie>> =
        flow {
            movieRepository.getSimilarMovies(id = id).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}