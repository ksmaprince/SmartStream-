package com.khun.smartstream.data.repositories

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
import com.khun.smartstream.data.remote.helper.IMovieHelper
import com.khun.smartstream.data.util.NetworkResult
import com.khun.smartstream.domain.repositories.IMovieRepository
import com.khun.smartstream.utils.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class IMovieRepositoryImpl(private val movieHelper: IMovieHelper) : IMovieRepository {
    override suspend fun getUpCommingMovies(page: Int): Flow<NetworkResult<UpComming>> =
        flow<NetworkResult<UpComming>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getUpCommingMovies(page = page)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getTopRatedMovies(page: Int): Flow<NetworkResult<TopRated>> =
        flow<NetworkResult<TopRated>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getTopRatedMovies(page = page)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getPopular(page: Int): Flow<NetworkResult<Popular>> =
        flow<NetworkResult<Popular>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getPopular(page = page)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getNowPlaying(page: Int): Flow<NetworkResult<NowPlaying>> =
        flow<NetworkResult<NowPlaying>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getNowPlaying(page = page)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getMovieDetail(id: Int): Flow<NetworkResult<Detail>> =
        flow<NetworkResult<Detail>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getMovieDetail(id = id)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getVideo(id: Int): Flow<NetworkResult<Video>> =
        flow<NetworkResult<Video>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getVideo(id = id)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getSimilarMovies(id: Int): Flow<NetworkResult<SimilarMovie>> =
        flow<NetworkResult<SimilarMovie>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getSimilarMovies(id = id)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getImages(id: Int): Flow<NetworkResult<Image>> =
        flow<NetworkResult<Image>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getImages(id = id)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getCredit(id: Int): Flow<NetworkResult<Credit>> =
        flow<NetworkResult<Credit>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getCredit(id = id)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getMovieReview(id: Int): Flow<NetworkResult<Review>> =
        flow<NetworkResult<Review>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getMovieReview(id = id)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getCategories(): Flow<NetworkResult<Category>> =
        flow<NetworkResult<Category>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getCategories()) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getTrendingMovies(
        timeWindow: TimeWindow
    ): Flow<NetworkResult<TrendingMovie>> =
        flow<NetworkResult<TrendingMovie>> {
            emit(NetworkResult.Loading())
            with(
                movieHelper.getTrendingMovies(
                    timeWindow = timeWindow.value
                )
            ) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getTrendingTV(timeWindow: TimeWindow): Flow<NetworkResult<TrendingTV>> =
        flow<NetworkResult<TrendingTV>> {
            emit(NetworkResult.Loading())
            with(
                movieHelper.getTrendingTV(
                    timeWindow = timeWindow.value
                )
            ) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun getMoviesByCategory(genreId: Int): Flow<NetworkResult<MovieByCategory>> =
        flow<NetworkResult<MovieByCategory>> {
            emit(NetworkResult.Loading())
            with(movieHelper.getMoviesByCategory(genreId = genreId)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }

    override suspend fun searchMovies(query: String): Flow<NetworkResult<Search>> =
        flow<NetworkResult<Search>> {
            emit(NetworkResult.Loading())
            with(movieHelper.searchMovies(query = query)) {
                if (isSuccessful) {
                    this.body()?.let {
                        emit(NetworkResult.Success(it))
                    }
                } else {
                    //Parse Error Body
                    emit(NetworkResult.Error(this.message()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }
}