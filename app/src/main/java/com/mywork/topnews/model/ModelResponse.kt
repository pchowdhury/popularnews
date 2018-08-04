package com.mywork.topnews.model

import android.support.annotation.Nullable


/**
 * Created by Pushpan on 3/08/18.
 */
class ModelResponse(val status: Long, @Nullable val value: List<Article>, @Nullable val error: Throwable?) {

    companion object {
        @JvmStatic
        val Loading = 0L
        @JvmStatic
        val Success = 1L
        @JvmStatic
        val Error = 2L

        @JvmStatic
        fun success(value: List<Article>): ModelResponse = ModelResponse(Success, value,  null)

        @JvmStatic
        fun error(value: Throwable?): ModelResponse = ModelResponse(Error, ArrayList(), value)

        @JvmStatic
        fun loading(): ModelResponse = ModelResponse(Loading, ArrayList(), null)
    }
}