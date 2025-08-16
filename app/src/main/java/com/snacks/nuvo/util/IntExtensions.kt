package com.snacks.nuvo.util

import java.text.NumberFormat
import java.util.Locale

fun Int?.toCommaFormat(): String {
    return this?.let { NumberFormat.getNumberInstance(Locale.getDefault()).format(it) } ?: ""
}
