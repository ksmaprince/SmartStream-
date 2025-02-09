package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.Category
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetCategoriesUseCase {
    fun invoke(): Flow<NetworkResult<Category>>
}