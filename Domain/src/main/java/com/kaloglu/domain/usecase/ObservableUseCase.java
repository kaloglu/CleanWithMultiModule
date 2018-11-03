package com.kaloglu.domain.usecase;

import com.kaloglu.domain.executor.PostExecutionThread;
import com.kaloglu.domain.executor.ThreadExecutor;
import com.kaloglu.domain.rxcallback.CallbackWrapper;
import com.kaloglu.domain.remote.BaseResponse;
import com.kaloglu.domain.remote.Status;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class ObservableUseCase<T extends BaseResponse, Params> {

    private final Scheduler mThreadScheduler;
    private final PostExecutionThread mPostExecutionThread;

    private final CompositeDisposable mDisposables;

    public ObservableUseCase(PostExecutionThread postExecutionThread) {
        this.mThreadScheduler = Schedulers.io();
        this.mPostExecutionThread = postExecutionThread;
        this.mDisposables = new CompositeDisposable();
    }

    public ObservableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.mThreadScheduler = Schedulers.from(threadExecutor);
        this.mPostExecutionThread = postExecutionThread;
        this.mDisposables = new CompositeDisposable();
    }

    public abstract Observable<T> buildUseCaseObservable(Params... params);

    public void execute(CallbackWrapper<T> callbackWrapper, Params... params) {
        if (callbackWrapper == null) return;

        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(mThreadScheduler)
                .observeOn(mPostExecutionThread.getScheduler());

        addDisposable(observable.subscribeWith(new DisposableObserver<T>() {

            @Override
            public void onNext(T t) {
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

            @Override
            public void onComplete() {
                //Nothing is done
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
