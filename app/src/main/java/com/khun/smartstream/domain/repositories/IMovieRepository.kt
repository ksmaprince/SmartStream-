package com.khun.smartstream.domain.repositories

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
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.utils.TimeWindow
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getUpCommingMovies(page: Int): Flow<NetworkResult<UpComming>>
    suspend fun getTopRatedMovies(page: Int): Flow<NetworkResult<TopRated>>
    suspend fun getPopular(page: Int): Flow<NetworkResult<Popular>>
    suspend fun getNowPlaying(page: Int): Flow<NetworkResult<NowPlaying>>
    suspend fun getMovieDetail(id: Int): Flow<NetworkResult<Detail>>
    suspend fun getVideo(id: Int): Flow<NetworkResult<Video>>
    suspend fun getSimilarMovies(id: Int): Flow<NetworkResult<SimilarMovie>>
    suspend fun getImages(id: Int): Flow<NetworkResult<Image>>
    suspend fun getCredit(id: Int): Flow<NetworkResult<Credit>>
    suspend fun getMovieReview(id: Int): Flow<NetworkResult<Review>>
    suspend fun getCategories(): Flow<NetworkResult<Category>>
    suspend fun getTrendingMovies(timeWindow: TimeWindow): Flow<NetworkResult<TrendingMovie>>
    suspend fun getTrendingTV(timeWindow: TimeWindow): Flow<NetworkResult<TrendingTV>>
    suspend fun getMoviesByCategory(genreId: Int): Flow<NetworkResult<MovieByCategory>>
    suspend fun searchMovies(query: String): Flow<NetworkResult<Search>>
}