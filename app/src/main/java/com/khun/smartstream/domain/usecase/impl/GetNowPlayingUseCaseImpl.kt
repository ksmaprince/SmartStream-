package com.khun.smartstream.domain.usecase.impl

import com.khun.smartstream.data.models.NowPlaying
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetNowPlayingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNowPlayingUseCaseImpl(private val movieRepository: IMovieRepository) :
    IGetNowPlayingUseCase {
    override fun invoke(page: Int): Flow<NetworkResult<NowPlaying>> =
        flow {
            movieRepository.getNowPlaying(page = page).collect {
                when(it){
                    is NetworkResult.Loading -> emit(NetworkResult.Loading())
                    is NetworkResult.Success -> emit(NetworkResult.Success(it.data))
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.message))
                }
            }
        }

}