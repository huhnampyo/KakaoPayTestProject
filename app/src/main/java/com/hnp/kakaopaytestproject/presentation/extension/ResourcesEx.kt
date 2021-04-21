package com.hnp.kakaopaytestproject.presentation.extension

import android.content.res.Resources

inline fun Resources.getStatusBarHeight(): Int = getIdentifier(
    "status_bar_height",
    "dimen",
    "android"
).let { resourceId ->
    if (resourceId > 0) {
        getDimensionPixelSize(resourceId)
    } else {
        0
    }
}
