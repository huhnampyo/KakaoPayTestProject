package com.hnp.kakaopaytestproject.presentation.extension

fun Double.format(digits: Int) = "%.${digits}f".format(this)