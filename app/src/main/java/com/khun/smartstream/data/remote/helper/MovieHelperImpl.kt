package com.khun.smartstream.data.remote.helper

import com.khun.smartstream.data.model.Category
import com.khun.smartstream.data.model.Credit
import com.khun.smartstream.data.model.Detail
import com.khun.smartstream.data.model.Image
import com.khun.smartstream.data.model.MovieByCategory
import com.khun.smartstream.data.model.NowPlaying
import com.khun.smartstream.data.model.Popular
import com.khun.smartstream.data.model.Review
import com.khun.smartstream.data.model.Search
import com.khun.smartstream.data.model.SimilarMovie
import com.khun.smartstream.data.model.TopRated
import com.khun.smartstream.data.model.TrendingMovie
import com.khun.smartstream.data.model.TrendingTV
import com.khun.smartstream.data.model.UpComming
import com.khun.smartstream.data.model.Video
import com.khun.smartstream.data.remote.MovieApi
import retrofit2.Response

class MovieHelperImpl(private val movieApi: MovieApi) : IMovieHelper {
    override suspend fun getUpCommingMovies(page: Int): Response<UpComming> =
        movieApi.getUpCommingMovies(page = page)

    override suspend fun getTopRatedMovies(page: Int): Response<TopRated> =
        movieApi.getTopRatedMovies(page = page)

    override suspend fun getPopular(page: Int): Response<Popular> =
        movieApi.getPopular(page = page)

    override suspend fun getNowPlaying(page: Int): Response<NowPlaying> =
        movieApi.getNowPlaying(page = page)

    override suspend fun getMovieDetail(id: Int): Response<Detail> =
        movieApi.getMovieDetail(id = id)

    override suspend fun getVideo(id: Int): Response<Video> =
        movieApi.getVideo(id = id)

    override suspend fun getSimilarMovies(id: Int): Response<SimilarMovie> =
        movieApi.getSimilarMovies(id = id)

    override suspend fun getImages(id: Int): Response<Image> =
        movieApi.getImages(id = id)

    override suspend fun getCredit(id: Int): Response<Credit> =
        movieApi.getCredit(id = id)

    override suspend fun getMovieReview(id: Int): Response<Review> =
        movieApi.getMovieReview(id = id)

    override suspend fun getCategories(): Response<Category> =
        movieApi.getCategories()

    override suspend fun getTrendingMovies(timeWindow: String): Response<TrendingMovie> =
        movieApi.getTrendingMovie(timeWindow = timeWindow)

    override suspend fun getTrendingTV(timeWindow: String): Response<TrendingTV> =
        movieApi.getTrendingTV(timeWindow = timeWindow)

    override suspend fun getMoviesByCategory(genreId: Int): Response<MovieByCategory> =
        movieApi.getMoviesByCategory(genreId = genreId)

    override suspend fun searchMovies(query: String): Response<Search> =
        movieApi.searchMovies(query = query)
}