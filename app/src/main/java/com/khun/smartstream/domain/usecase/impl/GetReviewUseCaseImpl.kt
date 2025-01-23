package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.models.Review
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetReviewUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetReviewUseCaseImpl(private val movieRepository: IMovieRepository) : IGetReviewUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<Review>> =
        flow {
            movieRepository.getMovieReview(id = id).collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}