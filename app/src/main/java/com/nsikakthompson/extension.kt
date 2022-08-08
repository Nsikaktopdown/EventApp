package com.nsikakthompson

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

private val INPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

@SuppressLint("SimpleDateFormat")
fun String.formatDate(): String {
    return try {
        SimpleDateFormat("EEE, d MMM yyyy").format(SimpleDateFormat(INPUT_DATE_FORMAT).parse(this))
    } catch (e: ParseException) {
        ""
    }
}

fun String.formatTime(): String {
    return try {
        SimpleDateFormat("hh:mm:ss a").format(SimpleDateFormat(INPUT_DATE_FORMAT).parse(this))
    } catch (e: ParseException) {
        ""
    }
}