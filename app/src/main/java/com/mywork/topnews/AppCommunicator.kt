package com.mywork.topnews

import com.mywork.topnews.model.Article

interface AppCommunicator{
    fun hasTwoPane():Boolean
    fun showDetails(title:String?, link:String?)
    fun getArticles():List<Article>
}