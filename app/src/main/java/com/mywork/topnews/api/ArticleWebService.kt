package com.mywork.topnews.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ArticleWebService {
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("all-sections/7.json?apikey=828e64b670894f49b24617fa4fdbc1d4")
    fun getArticles():
            io.reactivex.Observable<ArticleResponse>


    companion object {
        const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/"

        fun create(): ArticleWebService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ArticleWebService::class.java)
        }
    }

}