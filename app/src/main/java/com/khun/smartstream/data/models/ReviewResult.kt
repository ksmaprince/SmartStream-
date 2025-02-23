package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ReviewResult(
    val author: String?,
    val author_details: AuthorDetails?,
    val content: String?,
    val created_at: String?,
    val id: String?,
    val updated_at: String?,
    val url: String?
)