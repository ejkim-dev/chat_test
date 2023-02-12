package com.example.chattest.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chattest.domain.model.ResultModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

open class BaseViewModel(
    application: Application
) : AndroidViewModel(application) {
    protected val disposables = CompositeDisposable()
    protected val sessionExpired: PublishSubject<Boolean> = PublishSubject.create()

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _error : PublishSubject<String> = PublishSubject.create()
//    val error: LiveData<String> = _error
    val error : Observable<String> = _error

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    protected fun <T> getApiResult(
        single: Single<ResultModel<T>>,
        success: (T) -> Unit,
        showLoading: Boolean = true,
        tag: String
    ) {
        single.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { if (showLoading) _loading.postValue(true) }
            .doOnTerminate { if (showLoading) _loading.postValue(false) }
            .subscribeBy(onSuccess = {
                when (it) {
                    is ResultModel.Data -> success(it.data)
                    is ResultModel.Error -> {
                        _error.onNext("Result Error : ${it.throwable.message}")
                        Log.e("$tag", "getApiResult: ${it.throwable.message}")
                    }
                }
            }, onError = {
                _error.onNext("onError : ${it.message}")
                Log.e("$tag", "onError: ${it.message}")
            })
            .addTo(disposables)
    }
}