package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val backdrops: List<Backdrop>?,
    val id: Int?,
    val logos: List<Logo>?,
    val posters: List<Poster>?
)