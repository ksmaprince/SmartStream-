package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Int?,
    val results: List<VideoResult>?
)