package com.khun.smartstream.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int?,
    val name: String?
) {
    override fun toString(): String {
        return name?: ""
    }
}