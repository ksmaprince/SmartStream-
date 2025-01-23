package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Int?,
    val results: List<VideoResult>?
)