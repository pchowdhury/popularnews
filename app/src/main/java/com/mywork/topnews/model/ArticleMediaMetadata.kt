package com.mywork.topnews.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleMediaMetadata{

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null


}