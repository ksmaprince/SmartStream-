package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val id: Int?,
    val name: String?,
    val poster_path: String?,
    val backdrop_path: String?
)
