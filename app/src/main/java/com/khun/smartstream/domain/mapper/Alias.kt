package com.khun.smartstream.domain.mapper

import com.khun.smartstream.data.models.Backdrop
import com.khun.smartstream.data.models.Cast
import com.khun.smartstream.data.models.Crew
import com.khun.smartstream.data.models.Genre
import com.khun.smartstream.data.models.Poster
import com.khun.smartstream.data.models.Result
import com.khun.smartstream.data.models.ResultTV
import com.khun.smartstream.data.models.ReviewResult
import com.khun.smartstream.data.models.VideoResult

typealias Movie = Result
typealias Movies = List<Result>
typealias TV = ResultTV
typealias TVs = List<ResultTV>
typealias Categories = List<Genre>
typealias SimilarMovies = List<Result>
typealias RatingReview = ReviewResult
typealias RatingReviews = List<ReviewResult>
typealias BackDropImages = List<Backdrop>
typealias PosterImages = List<Poster>
typealias Crews = List<Crew>
typealias Casts = List<Cast>
typealias Video = VideoResult
typealias Videos = List<VideoResult>

