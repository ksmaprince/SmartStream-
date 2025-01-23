package com.khun.smartstream.domain.mapper

typealias ImageUriPath = String

fun ImageUriPath.toOriginalImageUrl(): String = "https://image.tmdb.org/t/p/original$this"
fun ImageUriPath.toNormalImageUrl(): String = "https://image.tmdb.org/t/p/w500$this"