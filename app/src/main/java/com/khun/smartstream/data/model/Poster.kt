package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Poster(
    val aspect_ratio: Double?,
    val height: Int?,
    val iso_639_1: String?,
    val file_path: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val width: Int?
)