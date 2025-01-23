package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.Search
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.ISearchMovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchMovieUseCaseImpl(private val movieRepository: IMovieRepository) : ISearchMovieUseCase {
    override fun invoke(query: String): Flow<NetworkResult<Search>> =
        flow {
            movieRepository.searchMovies(query = query).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}