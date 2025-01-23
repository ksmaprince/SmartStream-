package com.khun.smartstream.domain.mapper

import android.annotation.SuppressLint

fun Int.toHourAndMinute(): String {
    val hours = this / 60
    val minutes = this % 60
    return "${hours}h ${minutes}m"
}

@SuppressLint("DefaultLocale")
fun Double.toSingleDecimalString(): String {
    return String.format("%.1f", this)
}