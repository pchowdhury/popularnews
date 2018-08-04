package com.mywork.topnews.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mywork.topnews.api.ArticleWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ArticleViewModel: ViewModel(){
    val feedsResponse: MutableLiveData<ModelResponse> = MutableLiveData()
    private val disposable = CompositeDisposable()

    val apiService by lazy {
        ArticleWebService.create()
    }

    fun loadArticles(){
        if(feedsResponse.value == null || feedsResponse.value?.status == ModelResponse.Error){
            fetchArticles()
        }
    }

    fun fetchArticles(){
        feedsResponse.value = ModelResponse.loading()
        disposable.add(
                apiService.getArticles()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                        feedsResponse.value = ModelResponse.success(result.articles)
                                    },
                                { error -> feedsResponse.value = ModelResponse.error(error) }
        ))
    }

    @Throws(Exception::class)
    private fun getArticles():List<Article> {
        if(true){
            Thread.sleep(3000)
        return ArrayList()}else{
            throw Exception("test")
        }
    }
}