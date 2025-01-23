package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String?,
    val minimum: String?
)