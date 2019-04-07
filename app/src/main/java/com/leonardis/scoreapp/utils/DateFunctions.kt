package com.leonardis.scoreapp.utils

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val MONTH_FORMAT = "MMM dd, yyyy HH:mm"
const val DAY_FORMAT = "dd"
const val WEEK_DAY_FORMAT = "EEE"
const val ONLY_MONTH_FORMAT = "MMMM"

fun getDateFromFormatToFormat(date: String, initialFormat: String, finalFormat: String, locale: Locale): String {
    val dateToFormat = SimpleDateFormat(initialFormat, locale).parse(date)
    return SimpleDateFormat(finalFormat, locale).format(dateToFormat).orEmpty()
}

fun getDateWithFormat(date: String, format: String, locale: Locale = Locale.getDefault()): String =
    getDateFromFormatToFormat(date, DATE_FORMAT, format, locale)