package com.khun.smartstream.domain.mapper

fun String.truncateWithEllipsis(maxLength: Int): String {
    return if (this.length > maxLength) {
        this.take(maxLength - 3) + "..."
    } else {
        this
    }
}