package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CreatedBy(
    val id: Int?,
    val credit_id: String?,
    val name: String?,
    val original_name: String?,
    val gender: Int?,
    val profile_path: String?
)