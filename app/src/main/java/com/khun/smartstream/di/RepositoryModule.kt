package com.khun.smartstream.di

import com.khun.smartstream.data.remote.helper.IMovieHelper
import com.khun.smartstream.data.repositories.IMovieRepositoryImpl
import com.khun.smartstream.domain.repositories.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepository(movieHelper: IMovieHelper): IMovieRepository =
        IMovieRepositoryImpl(movieHelper = movieHelper)
}


//@InstallIn(SingletonComponent::class)
//@Module
//abstract class MovieRepositoryModule {
//
//    @Binds
//    abstract fun bindMovieRepository(
//        movieRepositoryImpl: MovieRepositoryImpl
//    ): MovieRepository
//}