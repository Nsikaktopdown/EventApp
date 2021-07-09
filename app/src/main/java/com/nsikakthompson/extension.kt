package com.nsikakthompson

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
private val INPUT_DATE_FORMAT= "yyyy-MM-dd'T'HH:mm:ss'Z'"
@SuppressLint("SimpleDateFormat")
fun String.formatDate() = SimpleDateFormat("EEE, d MMM yyyy").format(SimpleDateFormat(INPUT_DATE_FORMAT).parse(this))
fun String.formatTime() = SimpleDateFormat("hh:mm:ss a").format(SimpleDateFormat(INPUT_DATE_FORMAT).parse(this))