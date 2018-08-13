package com.mywork.topnews.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import com.mywork.topnews.api.ArticleWebService
import com.mywork.topnews.error.NewsException
import com.mywork.topnews.error.NoInternetException
import com.mywork.topnews.model.ModelResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class ArticleViewModel : ViewModel() {
    val feedsResponse: MutableLiveData<ModelResponse> = MutableLiveData()
    private val disposable = CompositeDisposable()

    private val apiService by lazy {
        ArticleWebService.create()
    }

    fun loadArticles() {
        if (feedsResponse.value == null || feedsResponse.value?.status == ModelResponse.Error) {
            fetchArticles()
        }
    }

    fun fetchArticles() {
        feedsResponse.value = ModelResponse.loading()
        disposable.add(
                apiService.getArticles()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    if (!TextUtils.isEmpty(result.status) && result.status?.toLowerCase() == "ok") {
                                        feedsResponse.value = ModelResponse.success(result.articles)
                                    } else {
                                        feedsResponse.value = ModelResponse.error(NewsException())
                                    }
                                },
                                { error ->
                                    if (error is UnknownHostException) {
                                        feedsResponse.value = ModelResponse.error(NoInternetException())
                                    } else {
                                        feedsResponse.value = ModelResponse.error(NewsException())
                                    }
                                }
                        ))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}