package com.khun.smartstream.di

import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.domain.usecase.IGetCategoriesUseCase
import com.khun.smartstream.domain.usecase.IGetCreditsUseCase
import com.khun.smartstream.domain.usecase.IGetDetailUseCase
import com.khun.smartstream.domain.usecase.IGetImagesUseCase
import com.khun.smartstream.domain.usecase.IGetMoviesByCategoryUseCase
import com.khun.smartstream.domain.usecase.IGetNowPlayingUseCase
import com.khun.smartstream.domain.usecase.IGetPopularUseCase
import com.khun.smartstream.domain.usecase.IGetReviewUseCase
import com.khun.smartstream.domain.usecase.IGetSimilarMovieUseCase
import com.khun.smartstream.domain.usecase.IGetTopRatedUseCase
import com.khun.smartstream.domain.usecase.IGetTrendingMovieUseCase
import com.khun.smartstream.domain.usecase.IGetTrendingTVUseCase
import com.khun.smartstream.domain.usecase.IGetUpCommingUseCase
import com.khun.smartstream.domain.usecase.IGetVideoUseCase
import com.khun.smartstream.domain.usecase.ISearchMovieUseCase
import com.khun.smartstream.domain.usecase.impl.GetCategoriesUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetCreditsUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetDetailUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetImagesUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetMoviesByCategoryUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetNowPlayingUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetPopularUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetReviewUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetSimilarMovieUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetTopRatedUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetTrendingMovieUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetTrendingTVUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetUpCommingUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.GetVideoUseCaseImpl
import com.khun.smartstream.domain.usecase.impl.SearchMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetUpCommingUseCase(movieRepository: IMovieRepository): IGetUpCommingUseCase =
        GetUpCommingUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetTopRatedUseCase(movieRepository: IMovieRepository): IGetTopRatedUseCase =
        GetTopRatedUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetPopularUseCase(movieRepository: IMovieRepository): IGetPopularUseCase =
        GetPopularUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetNowPlayingUseCase(movieRepository: IMovieRepository): IGetNowPlayingUseCase =
        GetNowPlayingUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetDetailUseCase(movieRepository: IMovieRepository): IGetDetailUseCase =
        GetDetailUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetVideoUseCase(movieRepository: IMovieRepository): IGetVideoUseCase =
        GetVideoUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetCategoriesUseCase(movieRepository: IMovieRepository): IGetCategoriesUseCase =
        GetCategoriesUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetMoviesByCategoriyUseCase(movieRepository: IMovieRepository): IGetMoviesByCategoryUseCase =
        GetMoviesByCategoryUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetTrendingMovieUseCase(movieRepository: IMovieRepository): IGetTrendingMovieUseCase =
        GetTrendingMovieUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetTrendingTVUseCase(movieRepository: IMovieRepository): IGetTrendingTVUseCase =
        GetTrendingTVUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideSimilarMovieUseCase(movieRepository: IMovieRepository): IGetSimilarMovieUseCase =
        GetSimilarMovieUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetImageUseCase(movieRepository: IMovieRepository): IGetImagesUseCase =
        GetImagesUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideGetCreditsUseCase(movieRepository: IMovieRepository): IGetCreditsUseCase =
        GetCreditsUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideMovieReviewUseCase(movieRepository: IMovieRepository): IGetReviewUseCase =
        GetReviewUseCaseImpl(movieRepository = movieRepository)

    @Provides
    fun provideSearchMovieUseCase(movieRepository: IMovieRepository): ISearchMovieUseCase =
        SearchMovieUseCaseImpl(movieRepository = movieRepository)


}