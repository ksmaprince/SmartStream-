package com.khun.smartstream.domain.repositories

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