package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    val id: Int?,
    val cast: List<Cast>?,
    val crew: List<Crew>?
)