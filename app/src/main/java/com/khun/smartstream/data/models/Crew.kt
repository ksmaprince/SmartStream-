package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val credit_id: String?,
    val department: String?,
    val job: String?
)