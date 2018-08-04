package com.mywork.topnews.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article{

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("published_date")
    @Expose
    var published_date: String? = null

    @SerializedName("media")
    @Expose
    var media: MutableList<ArticleMedia> = mutableListOf()


}