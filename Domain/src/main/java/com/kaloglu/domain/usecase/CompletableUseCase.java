package com.kaloglu.domain.usecase;

import com.kaloglu.domain.executor.PostExecutionThread;
import com.kaloglu.domain.executor.ThreadExecutor;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class CompletableUseCase<Params> {

    private final Scheduler mThreadScheduler;
    private final PostExecutionThread mPostExecutionThread;

    private final CompositeDisposable mDisposables;

    public CompletableUseCase(PostExecutionThread postExecutionThread) {
        this.mThreadScheduler = Schedulers.io();
        this.mPostExecutionThread = postExecutionThread;
        this.mDisposables = new CompositeDisposable();
    }

    public CompletableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.mThreadScheduler = Schedulers.from(threadExecutor);
        this.mPostExecutionThread = postExecutionThread;
        this.mDisposables = new CompositeDisposable();
    }

    abstract Completable buildUseCaseCompletable(Params... params);

    public void execute(DisposableCompletableObserver observer, Params... params) {
        if (observer == null) return;

        final Completable completable = this.buildUseCaseCompletable(params)
                .subscribeOn(mThreadScheduler)
                .observeOn(mPostExecutionThread.getScheduler());

        addDisposable(completable.subscribeWith(observer));
    }

    private void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }
}
