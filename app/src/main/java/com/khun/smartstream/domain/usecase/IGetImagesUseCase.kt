package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.model.Image
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetImagesUseCase {
    fun invoke(id: Int): Flow<NetworkResult<Image>>
}