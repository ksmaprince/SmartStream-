package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String?,
    val minimum: String?
)