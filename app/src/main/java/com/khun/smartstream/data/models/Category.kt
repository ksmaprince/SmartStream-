package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val genres: List<Genre>?
)