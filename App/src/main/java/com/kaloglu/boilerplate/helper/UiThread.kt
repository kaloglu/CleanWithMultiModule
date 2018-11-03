package com.kaloglu.boilerplate.helper

import com.kaloglu.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UiThread @Inject internal constructor() : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}