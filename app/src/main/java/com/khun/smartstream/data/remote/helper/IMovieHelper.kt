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