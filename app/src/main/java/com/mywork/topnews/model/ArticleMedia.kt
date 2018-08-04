package com.mywork.topnews.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleMedia{
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("media-metadata")
    @Expose
    var mediaMetaData: MutableList<ArticleMediaMetadata> = mutableListOf()

}
