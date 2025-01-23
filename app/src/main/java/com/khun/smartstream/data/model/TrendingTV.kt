package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TrendingTV(
    val page: Int?,
    val results: List<ResultTV>?,
    val total_pages: Int?,
    val total_results: Int?
)