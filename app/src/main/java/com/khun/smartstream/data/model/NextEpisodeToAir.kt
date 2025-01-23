package com.khun.smartstream.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NextEpisodeToAir(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val vote_average: Int?,
    val vote_count: Int?,
    val air_date: String?,
    val episode_number: Int?,
    val episode_type: String?,
    val production_code: String?,
    val runtime: Int?,
    val season_number: Int?,
    val show_id: Int?,
    val still_path: String?
)