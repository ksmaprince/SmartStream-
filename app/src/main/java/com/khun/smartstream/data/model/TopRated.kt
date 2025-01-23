package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TopRated(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)