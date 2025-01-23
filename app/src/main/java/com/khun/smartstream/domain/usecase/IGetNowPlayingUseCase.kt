package com.khun.smartstream.domain.usecase

import com.khun.smartstream.data.model.NowPlaying
import com.khun.smartstream.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IGetNowPlayingUseCase {
    fun invoke(page: Int): Flow<NetworkResult<NowPlaying>>
}