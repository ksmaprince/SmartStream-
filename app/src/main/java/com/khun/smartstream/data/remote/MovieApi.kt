package com.khun.smartstream.data.remote

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
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    //Movies

    @GET("/3/movie/upcoming")
    suspend fun getUpCommingMovies(
        @Query("page") page: Int
    ): Response<UpComming>

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): Response<TopRated>

    @GET("/3/movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): Response<Popular>


    @GET("/3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): Response<NowPlaying>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int
    ): Response<Detail>

    @GET("/3/movie/{id}/videos")
    suspend fun getVideo(
        @Path("id") id: Int
    ): Response<Video>

    @GET("/3/movie/{id}/reviews")
    suspend fun getMovieReview(
        @Path("id") id: Int
    ): Response<Review>

    @GET("/3/movie/{id}/similar")
    suspend fun getSimilarMovies(
        @Path("id") id: Int
    ): Response<SimilarMovie>

    @GET("/3/movie/{id}/credits")
    suspend fun getCredit(
        @Path("id") id: Int
    ): Response<Credit>

    @GET("/3/movie/{id}/images")
    suspend fun getImages(
        @Path("id") id: Int
    ): Response<Image>

    @GET("/3/genre/movie/list")
    suspend fun getCategories(): Response<Category>

    @GET("/3/trending/movie/{timewindow}")
    suspend fun getTrendingMovie(
        @Path("timewindow") timeWindow: String
    ): Response<TrendingMovie>

    @GET("/3/trending/tv/{timewindow}")
    suspend fun getTrendingTV(
        @Path("timewindow") timeWindow: String
    ): Response<TrendingTV>

    @GET("/3/discover/movie")
    suspend fun getMoviesByCategory(
        @Query("with_genres") genreId: Int
    ): Response<MovieByCategory>

    @GET("/3/search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): Response<Search>
}