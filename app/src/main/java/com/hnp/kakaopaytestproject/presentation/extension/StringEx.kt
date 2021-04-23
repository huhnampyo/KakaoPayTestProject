package com.hnp.kakaopaytestproject.presentation.extension

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import kotlin.math.abs

fun String.toFormattedDate(): String {
    return when {
        isEmpty() -> {
            ""
        }
        length <= 6 -> {
            "${substring(0, 4)}.${substring(4, 6)}"
        }
        else -> {
            "${substring(0, 4)}.${substring(4, 6)}.${substring(6, 8)}"
        }
    }
}

fun String.toFormattedDateYMD(pattern: String): String {
    return if(isEmpty()) ""
    else {
        val data = SimpleDateFormat("yyyyMMdd").parse(this)
        SimpleDateFormat(pattern).format(data)
    }
}

/**
 * format : "#"
 *  12 -> 12
 *  12.3456 -> 12
 *
 *  format : "#.##"
 *   12 -> 12
 *   12.3456 -> 12.34
 *   -12.3456 -> -12.34
 */
fun String.toFormatted(format: String): String {
    val df = DecimalFormat(format)
    df.roundingMode = RoundingMode.DOWN

    return if(isEmpty()) {
        ""
    } else {
        df.format(toDouble())
    }
}

fun String.toFormattedAbs(format: String): String {
    val df = DecimalFormat(format)
    df.roundingMode = RoundingMode.DOWN

    return if(isEmpty()) {
        ""
    } else {
        df.format(abs(toDouble()))
    }
}

fun String.isEmptyOrZero(): Boolean {
    return if(isEmpty()) {
        true
    } else {
        toInt() == 0
    }
}

fun String.toFormattedPrice(만여부: Boolean): String {
    return when {
        isEmpty() -> {
            ""
        }
        length <= 4 -> {
            if(isEmptyOrZero()) ""
            else {
                if(만여부) "${toFormatted("###,###")}만"
                else toFormatted("###,###")
            }
        }
        else -> {
            val splitIndex = length - 4
            val 억 = substring(0, splitIndex)
            val 만 = substring(splitIndex, length)

            if(만.isEmptyOrZero()) {
                "${억}억"
            } else {
                if(만여부) "${억}억 ${만.toFormatted("###,###")}만"
                else "${억}억 ${만.toFormatted("###,###")}"
            }
        }
    }
}

fun String.getConvertDateToString(customFormat: String):String{
    return try{
        val dtf: org.joda.time.format.DateTimeFormatter? = ISODateTimeFormat.dateTime()
        val parsedDate: org.joda.time.LocalDateTime? = dtf?.parseLocalDateTime(this)
        parsedDate?.toString(DateTimeFormat.forPattern(customFormat))
            ?: "-"
    }catch (e : IllegalArgumentException){
        "-"
    }

}