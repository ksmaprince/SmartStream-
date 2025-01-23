package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetails(
    val name: String?,
    val username: String?,
    val avatar_path: String?,
    val rating: Double?
)