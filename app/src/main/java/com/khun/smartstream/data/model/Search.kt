package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Search(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)