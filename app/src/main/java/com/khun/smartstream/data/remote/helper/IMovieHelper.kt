package com.khun.smartstream.data.remote.helper

import com.khun.smartstream.data.models.Category
import com.khun.smartstream.data.models.Credit
import com.khun.smartstream.data.models.Detail
import com.khun.smartstream.data.models.Image
import com.khun.smartstream.data.models.MovieByCategory
import com.khun.smartstream.data.models.NowPlaying
import com.khun.smartstream.data.models.Popular
import com.khun.smartstream.data.models.Review
import com.khun.smartstream.data.models.Search
import com.khun.smartstream.data.models.SimilarMovie
import com.khun.smartstream.data.models.TopRated
import com.khun.smartstream.data.models.TrendingMovie
import com.khun.smartstream.data.models.TrendingTV
import com.khun.smartstream.data.models.UpComming
import com.khun.smartstream.data.models.Video
import retrofit2.Response

interface IMovieHelper {
    suspend fun getUpCommingMovies(page: Int): Response<UpComming>
    suspend fun getTopRatedMovies(page: Int): Response<TopRated>
    suspend fun getPopular(page: Int): Response<Popular>
    suspend fun getNowPlaying(page: Int): Response<NowPlaying>
    suspend fun getMovieDetail(id: Int): Response<Detail>
    suspend fun getVideo(id: Int): Response<Video>
    suspend fun getSimilarMovies(id: Int): Response<SimilarMovie>
    suspend fun getImages(id: Int): Response<Image>
    suspend fun getCredit(id: Int): Response<Credit>
    suspend fun getMovieReview(id: Int): Response<Review>
    suspend fun getCategories(): Response<Category>
    suspend fun getTrendingMovies(timeWindow: String): Response<TrendingMovie>
    suspend fun getTrendingTV(timeWindow: String): Response<TrendingTV>
    suspend fun getMoviesByCategory(genreId: Int): Response<MovieByCategory>
    suspend fun searchMovies(query: String): Response<Search>
}