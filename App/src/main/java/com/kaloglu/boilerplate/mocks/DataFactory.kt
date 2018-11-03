package com.kaloglu.boilerplate.mocks

import java.util.Random
import java.util.UUID

//TODO: just mock data
object DataFactory {

    private fun randomUuid() = UUID.randomUUID().toString()

    fun randomInt(bound: Int) = Random().nextInt(bound)

    fun randomLongString() = (Random().nextLong() + 1).toString()

    private fun randomDoubleString() = (Random().nextDouble() + 1)

    fun randomBoolean() = Math.random() < 0.5

//    fun makeEventList(neverEmpty: Boolean = false): List<Event> {
//        val items: MutableList<Event> = mutableListOf()
//        when {
//            randomInt(3) > 0 || neverEmpty -> {
//                repeat(randomInt(4) + 1) {
//                    items.add(generateEvent())
//                }
//            }
//        }
//        return items
//    }
//
//    fun makeCouponList(): List<Coupon> {
//        val items: MutableList<Coupon> = mutableListOf()
//        val couponCount = randomInt(30)
//        when {
//            /*randomBoolean() &&*/ couponCount > 0 -> {
//            repeat(couponCount) {
//                items.add(generateCoupon())
//            }
//        }
//        }
//        return items
//    }
//
//    private fun generateCoupon(): Coupon {
//        return Coupon().withStatusCode<Coupon>(200).apply {
//            couponName = if (randomBoolean() && randomBoolean() && randomBoolean()) randomUuid() else ""
//            date = Date().addDay(randomInt(3)).time
//            matchCount = randomInt(10) + 1
//            totalRatio = randomDoubleString() * matchCount
//            couponCount = if (randomBoolean() && randomBoolean()) randomInt(100) else 1
//            multiplier = randomInt(20)
//            couponCost = (multiplier * 3).toDouble()
//            earnings = (couponCost * totalRatio)
//            canCancel = randomBoolean() && randomBoolean()
//            canShare = randomBoolean() && randomBoolean()
//            canPlay = randomBoolean() && randomBoolean()
//            type = randomInt(3)
//            note = when (type) {
//                1 -> "Gece Maçları"
//                2 -> "kaloglu kuponu"
//                else -> ""
//            }
//            events?.toMutableList()
//                    ?.apply {
//                        repeat(matchCount) {
//                            add(generateEvent())
//                        }
//                    }
//            system = makeSystemList(events?.size ?: 0)
//                    .asSequence()
//                    .filter { it.isChecked }
//                    .map { it.value.toInt() }
//                    .toList()
//        }
//    }
//
//    fun generateEvent(count: Int = randomInt(3) + 1, oddType: String = "MS"): Event {
//        val eventCode = randomInt(100).toString()
//        val builder = Event.Builder()
//                .setCode(eventCode)
//                .setName(getEventName())
//                .setMinSelectionCount(randomInt(3) + 1)
//                .setHasAnyValuesChanged(randomBoolean())
//                .setHasLiveStream(randomBoolean())
//                .setOdds(arrayListOf<Odd>().apply {
//                    repeat(count) {
//                        add(generateOdd(eventCode, oddType))
//                    }
//                })
//        when {
//            randomBoolean() -> builder.setLimit(randomInt(2).toFloat())
//            randomBoolean() -> builder.setFinalTimeHandicapAway(randomInt(2).toFloat())
//            randomBoolean() -> builder.setFinalTimeHandicapHome(randomInt(2).toFloat())
//        }
//
//        return builder.build()
//    }
//
//    private fun getEventName(): String {
//        return when (randomInt(5) + 1) {
//            0 -> "Beşiktaş - Galatasaray"
//            1 -> "Fenerbahçe - Galatasaray"
//            2 -> "Beşiktaş - Fenerbahçe"
//            3 -> "Barcelona - Real Madrid"
//            4 -> "Beşiktaş - Paris Saint Germain"
//            5 -> "inter - Porto"
//            else -> "Beşiktaş - Villareal"
//        }
//    }
//
//    fun generateOdd(code: String, oddType: String = "MS"): Odd {
//        return Odd.Builder()
//                .setCode(code)
//                .setName((Random().nextInt(2) + 1).toString())
//                .setOdd(
//                        OddChange.Builder()
//                                .setValue(randomDoubleString().toString())
//                                .build()
//                )
//                .setOddType(oddType)
//                .build()
//    }
//
//    fun makeSystemList(eventCount: Int = 0): MutableList<SystemItem> {
//        val items: MutableList<SystemItem> = mutableListOf()
//        repeat(randomInt(eventCount)) {
//            items.add(generateSystemItem(it.toString()))
//        }
//        return items
//    }
//
//    private fun generateSystemItem(value: String): SystemItem = SystemItem(value, randomBoolean())

    @JvmOverloads
    fun randomScore(score: String = ""): String {
        val home: Int
        val away: Int
        if (randomBoolean() && randomBoolean())
            return "" //  score gelmedi

        return when (score) {
            "" -> {
                val type = randomBoolean() // false: "basketbol" true: "futbol"
                home = randomInt(if (type) 5 else 100) + 1
                away = randomInt(if (type) 5 else 100) + 1

                home.toString() + "-" + away.toString()
            }
            else -> {
                home = randomInt(score.split("-")[0].toInt()) + 1
                away = randomInt(score.split("-")[1].toInt()) + 1

                "İY:" + home.toString() + "-" + away.toString()
            }
        }
    }

    fun randomDate(started: Boolean): String {
        if (started)
            return ""

        return when (randomInt(3) + 1) {
            1 -> "Bugün 22:30"
            2 -> "29 Aralık 16:00"
            else -> "Yarın 21:00"
        }
    }

}