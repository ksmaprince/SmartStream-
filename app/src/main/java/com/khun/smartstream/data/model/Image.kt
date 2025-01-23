package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val backdrops: List<Backdrop>?,
    val id: Int?,
    val logos: List<Logo>?,
    val posters: List<Poster>?
)