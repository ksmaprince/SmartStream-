package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.SimilarMovie
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetSimilarMovieUseCase {
    fun invoke(id: Int): Flow<NetworkResult<SimilarMovie>>
}