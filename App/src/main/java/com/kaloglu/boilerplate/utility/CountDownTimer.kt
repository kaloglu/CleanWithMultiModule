package com.kaloglu.boilerplate.utility

import java.util.concurrent.TimeUnit

class CountDownTimer(private val seconds: Long, val countDownListener: CountDownListener? = null) {

    private var countDownTimer: android.os.CountDownTimer

    interface CountDownListener {
        fun onTick(secondsUntilFinished: Long)
        fun onFinish()
    }

    init {
        countDownTimer = createTimer(seconds)
    }

    fun start() {
        countDownTimer.start()
    }

    fun stop() {
        countDownTimer.cancel()
    }

    fun restart(seconds: Long = this.seconds) {
        countDownTimer.cancel()
        if (seconds != this.seconds) countDownTimer = createTimer(seconds)
        countDownTimer.start()
    }

    private fun createTimer(seconds: Long, tickInterval: Long = 1000L): android.os.CountDownTimer {
        val milliseconds = TimeUnit.SECONDS.toMillis(seconds)

        return object : android.os.CountDownTimer(milliseconds, tickInterval) {

            override fun onTick(millisUntilFinished: Long) {
                countDownListener?.onTick(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))
            }

            override fun onFinish() {
                countDownListener?.onFinish()
            }
        }
    }
}
