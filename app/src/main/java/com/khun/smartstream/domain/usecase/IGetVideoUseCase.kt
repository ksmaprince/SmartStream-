package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.models.Video
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetVideoUseCase {
    fun invoke(id: Int): Flow<NetworkResult<Video>>
}