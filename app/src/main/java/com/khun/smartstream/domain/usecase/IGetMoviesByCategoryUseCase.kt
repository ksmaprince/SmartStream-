package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.MovieByCategory
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetMoviesByCategoryUseCase {
    fun invoke(genreId: Int): Flow<NetworkResult<MovieByCategory>>
}