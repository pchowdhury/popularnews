package com.mywork.topnews.api
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mywork.topnews.model.Article

class ArticleResponse {
    @SerializedName("status")
    @Expose
    var status: String? = ""
    @SerializedName("num_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("results")
    @Expose
    var articles: MutableList<Article> = mutableListOf()

}