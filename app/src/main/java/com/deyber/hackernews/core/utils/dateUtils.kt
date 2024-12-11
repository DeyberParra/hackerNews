package com.deyber.hackernews.core.utils

import com.deyber.hackernews.core.utils.DateConfigs.DEFAULT_FORMAT_DATE
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

object  DateConfigs{
    const val DEFAULT_FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
}

fun Long.toTimeAgo(): String {
    val now = System.currentTimeMillis()
    val diff = now - this

    return when {
        diff < TimeUnit.MINUTES.toMillis(1) -> "just now"
        diff < TimeUnit.HOURS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toMinutes(diff)}m"
        diff < TimeUnit.DAYS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toHours(diff)}h"
        diff < TimeUnit.DAYS.toMillis(2) -> "yesterday"
        else -> "${TimeUnit.MILLISECONDS.toDays(diff)}d"
    }
}

fun String.toMillis(dateFormat: String = DEFAULT_FORMAT_DATE): Long {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    return formatter.parse(this)?.time ?: 0L
}

fun String.toTimeAgoFromString(dateFormat: String = DEFAULT_FORMAT_DATE): String {
    val timeStamp = this.toMillis(dateFormat)
    return timeStamp.toTimeAgo()
}
