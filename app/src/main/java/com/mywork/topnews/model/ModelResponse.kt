package com.mywork.topnews.model

import android.support.annotation.Nullable


/**
 * Created by Pushpan on 3/08/18.
 */
class ModelResponse(val status: Int, @Nullable val value: List<Article>, @Nullable val error: Throwable?) {

    companion object {
        @JvmStatic
        val Loading = 0
        @JvmStatic
        val Success = 1
        @JvmStatic
        val Error = 2

        @JvmStatic
        fun success(value: List<Article>): ModelResponse = ModelResponse(Success, value,  null)

        @JvmStatic
        fun error(value: Throwable?): ModelResponse = ModelResponse(Error, ArrayList(), value)

        @JvmStatic
        fun loading(): ModelResponse = ModelResponse(Loading, ArrayList(), null)
    }
}