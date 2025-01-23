package com.khun.smartstream.data.model

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