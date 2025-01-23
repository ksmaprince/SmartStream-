package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    val id: Int?,
    val cast: List<Cast>?,
    val crew: List<Crew>?
)