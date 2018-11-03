@file:JvmName("Utility")

package com.kaloglu.boilerplate.utility.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

val LOCALE_TR = Locale("tr", "TR")
val currency = NumberFormat.getCurrencyInstance(LOCALE_TR)!!
var decimalFormat = NumberFormat.getInstance(LOCALE_TR) as DecimalFormat
var decimalFormatSymbols = DecimalFormatSymbols(LOCALE_TR)

fun String.formatDecimal(): String = toDouble().formatDecimal(isCurrency = false)
fun String.formatAmount(): String = toDouble().formatDecimal(true)

fun Double.formatAmount(): String = formatDecimal(true)

@JvmOverloads
fun Double.formatDecimal(isCurrency: Boolean = false): String {
    if (isCurrency)
        decimalFormat.applyPattern("#,###.00 TL")
    else
        decimalFormat.applyPattern("###.00")

    decimalFormatSymbols.decimalSeparator = ','
    decimalFormatSymbols.groupingSeparator = '.'

    decimalFormat.decimalFormatSymbols = decimalFormatSymbols

    return decimalFormat.format(this)
}

fun Double.formatRealAmount() = when {
    this > 500000.0 -> "500000+".formatAmount()
    else -> formatAmount()
}

fun Double.clearUnneccessaryZero(): String {
    val limitStr = this.toString()
    return when {
        limitStr
                .contains(".") -> limitStr
                .replace("0*$".toRegex(), "")
                .replace("\\.$".toRegex(), "")
        else -> limitStr
    }
}