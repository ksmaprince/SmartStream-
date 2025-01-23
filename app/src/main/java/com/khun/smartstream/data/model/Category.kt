package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val genres: List<Genre>?
)