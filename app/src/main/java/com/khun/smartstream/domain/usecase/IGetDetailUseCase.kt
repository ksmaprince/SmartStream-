package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.model.Detail
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetDetailUseCase {
    fun invoke(id: Int): Flow<NetworkResult<Detail>>
}