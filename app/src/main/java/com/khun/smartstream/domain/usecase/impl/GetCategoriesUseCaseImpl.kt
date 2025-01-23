package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.model.Category
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetCategoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoriesUseCaseImpl(private val movieRepository: IMovieRepository) :
    IGetCategoriesUseCase {
    override fun invoke(): Flow<NetworkResult<Category>> =
        flow {
            movieRepository.getCategories().collect {
                when (it) {
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }
}