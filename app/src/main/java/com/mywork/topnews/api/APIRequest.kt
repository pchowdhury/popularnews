package com.mywork.topnews.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIRequest {
    @GET("v2/top-headlines")
    fun getHeadLine(@Query("country") country: String,
                    @Query("apikey") apiKey: String): Call<ArticleResponse>
}