package com.hnp.kakaopaytestproject.presentation.extension

import java.util.*

/**
 * 다음주 금요일 오전 9시를 리턴해줍니다.
 */
fun Calendar.getNextFridayTime() : Long{

    set(Calendar.HOUR_OF_DAY, 9)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)

    if(get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
        add(Calendar.DATE, 7)
    } else {
        add(Calendar.DATE, 1)
        while(get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
            add(Calendar.DATE, 1)
        }
    }
    return time.time
}

/*
* 내일 정각을 받아옵니다
* */
fun Calendar.getTomorrow() : Long{
    set(Calendar.HOUR_OF_DAY, 9)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    add(Calendar.DATE, 1)
    return time.time
}