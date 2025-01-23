package com.khun.smartstream.di

import com.khun.smartstream.data.remote.MovieApi
import com.khun.smartstream.data.remote.helper.IMovieHelper
import com.khun.smartstream.data.remote.helper.MovieHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HelperModule {
    @Provides
    fun provideMovieHelper(movieApi: MovieApi): IMovieHelper =
        MovieHelperImpl(movieApi = movieApi)
}