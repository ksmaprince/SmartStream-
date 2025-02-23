package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    val id: Int?,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)