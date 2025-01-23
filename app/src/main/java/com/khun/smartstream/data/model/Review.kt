package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int?,
    val page: Int?,
    val results: List<ReviewResult>?,
    val total_pages: Int?,
    val total_results: Int?
)