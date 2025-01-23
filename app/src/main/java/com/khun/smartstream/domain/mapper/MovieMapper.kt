package com.khun.smartstream.domain.mapper

import com.khun.smartstream.data.models.Result
import com.khun.smartstream.data.models.ResultTV

fun ResultTV.toMovie(): Result =
    Result(
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = genre_ids,
        id = id,
        original_language = original_language,
        original_title = original_name,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = first_air_date,
        title = name,
        video = false,
        vote_average = vote_average,
        vote_count = vote_count
    )

fun List<ResultTV>.toResultList(): List<Result> =
    this.map { it.toMovie() }.toList()

fun Result.toTV(): ResultTV =
    ResultTV(
        backdrop_path = backdrop_path,
        id = id,
        name = title,
        original_name = original_title,
        overview = overview,
        poster_path = poster_path,
        media_type = "",
        adult = adult,
        original_language = original_language,
        genre_ids = genre_ids,
        popularity = popularity,
        first_air_date = release_date,
        vote_average = vote_average,
        vote_count = vote_count,
        origin_country = listOf()
    )

fun List<Result>.toTVs(): List<ResultTV> =
    this.map { it.toTV() }.toList()


