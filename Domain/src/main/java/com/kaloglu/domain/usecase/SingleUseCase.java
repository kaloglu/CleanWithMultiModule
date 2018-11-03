package com.kaloglu.domain.usecase;

import com.kaloglu.domain.executor.PostExecutionThread;
import com.kaloglu.domain.executor.ThreadExecutor;
import com.kaloglu.domain.rxcallback.CallbackWrapper;
import com.kaloglu.domain.remote.BaseResponse;
import com.kaloglu.domain.remote.Status;

import java.io.IOException;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class SingleUseCase<T extends BaseResponse, Params> {

    private final Scheduler mThreadScheduler;
    private final PostExecutionThread mPostExecutionThread;

    private final CompositeDisposable mDisposables;

    public SingleUseCase(PostExecutionThread postExecutionThread) {
        this.mThreadScheduler = Schedulers.io();
        this.mPostExecutionThread = postExecutionThread;
        this.mDisposables = new CompositeDisposable();
    }

    public SingleUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.mThreadScheduler = Schedulers.from(threadExecutor);
        this.mPostExecutionThread = postExecutionThread;
        this.mDisposables = new CompositeDisposable();
    }

    public abstract Single<T> buildUseCaseSingle(Params... params);

    public void execute(CallbackWrapper<T> callbackWrapper, Params... params) {
        if (callbackWrapper == null) return;
        final Single<T> single = this.buildUseCaseSingle(params)
                .subscribeOn(mThreadScheduler)
                .observeOn(mPostExecutionThread.getScheduler());
        addDisposable(single.subscribeWith(new DisposableSingleObserver<T>() {
            @Override
            public void onSuccess(T t) {
                Status status = t.getStatus();
                if (status.getCode() == 200 && t.isSuccess()) {
                    callbackWrapper.onResponseSuccess(t);
                } else {
                    callbackWrapper.onResponseError(status, t.getErrors());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof IOException) {
                    callbackWrapper.onNetworkError(e);
                } else {
                    callbackWrapper.onServerError(e);
                }
            }
        }));
    }

    private void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    public void dispose() {
        if (!mDisposables.isDisposed()) mDisposables.dispose();
    }

}