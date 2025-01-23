package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.MovieByCategory
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetMoviesByCategoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMoviesByCategoryUseCaseImpl(private val movieRepository: IMovieRepository) :
    IGetMoviesByCategoryUseCase {
    override fun invoke(genreId: Int): Flow<NetworkResult<MovieByCategory>> =
        flow {
            movieRepository.getMoviesByCategory(genreId = genreId).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}