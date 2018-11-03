@file:JvmName("DateUtil")

package com.kaloglu.boilerplate.utility.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Date.isToday(): Boolean = isSameDay(Date())

fun Date.isTomorrow(): Boolean = isSameDay(Date().addDay(1))

fun Date.isSameDay(date: Date): Boolean = calendar().isSameDay(date)

fun Date.calendar(): Calendar = Calendar.getInstance().apply { time = this@calendar }

fun Calendar.isSameDay(date: Date): Boolean = isSameDay(date.calendar())

fun Calendar.isSameDay(cal: Calendar): Boolean =
        isSameYear(cal) && get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)

fun Calendar.isSameYear(cal: Calendar): Boolean = get(Calendar.YEAR) == cal.get(Calendar.YEAR)

fun Date.addDay(days: Int): Date = calendar().addDay(days).time

fun Calendar.addDay(days: Int): Calendar {
    add(Calendar.DATE, days)
    return this
}

fun Long.getMinutes(): Long {
    return TimeUnit.SECONDS.toMinutes(this)
}

fun Long.getSeconds(): Long {
    val minutesInSeconds = TimeUnit.MINUTES.toSeconds(getMinutes())
    return this - minutesInSeconds
}

fun Date.getMonthName(): String {
    return calendar().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale("tr", "TR"))
}

fun Date.getDayOfMonth(): Int {
    return calendar().get(Calendar.DAY_OF_MONTH)
}

fun Long.toDateTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM HH:mm", LOCALE_TR)
    return format.format(date)
}

fun currentTime(): Long {
    return System.currentTimeMillis()
}
