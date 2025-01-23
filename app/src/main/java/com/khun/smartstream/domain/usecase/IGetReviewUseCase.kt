package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.Review
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetReviewUseCase {
    fun invoke(id: Int): Flow<NetworkResult<Review>>
}