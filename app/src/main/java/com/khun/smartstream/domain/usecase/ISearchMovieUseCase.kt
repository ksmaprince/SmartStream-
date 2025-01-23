package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.model.Search
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ISearchMovieUseCase {
    fun invoke(query: String): Flow<NetworkResult<Search>>
}