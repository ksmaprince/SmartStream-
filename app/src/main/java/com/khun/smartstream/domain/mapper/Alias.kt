package com.khun.smartstream.domain.mapper

import com.khun.smartstream.data.model.Backdrop
import com.khun.smartstream.data.model.Cast
import com.khun.smartstream.data.model.Crew
import com.khun.smartstream.data.model.Genre
import com.khun.smartstream.data.model.Poster
import com.khun.smartstream.data.model.Result
import com.khun.smartstream.data.model.ResultTV
import com.khun.smartstream.data.model.ReviewResult
import com.khun.smartstream.data.model.VideoResult

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

