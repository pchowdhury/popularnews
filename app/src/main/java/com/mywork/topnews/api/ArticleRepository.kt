package com.mywork.topnews.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import retrofit2.Response

class ArticleRepository{

//    val INSTANCE = ArticleRepository()
//
//    companion object {
//        private var apiService = ArticleWebService.create()
//        fun getHeadLine(country:String, key:String): LiveData<ArticleResponse>
//        {
//
//            apiService.getHeadLine(country, key)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            { result -> showResult(result.query.searchinfo.totalhits) },
//                            { error -> showError(error.message) }
//                    )
//
//
//
//
//
//             val data = MutableLiveData<ArticleResponse>()
//           val response  = apiService.getHeadLine(country, key).execute()
//
//                    if(response.isSuccessful){
//
//                    }else{
//
//                    }
//
//                    .enqueue(new Callback<NewsResponse>()
//                    {
//                        @Override
//                        public void onResponse(Call<NewsResponse> call,     Response<NewsResponse> response)
//                        {
//                            if (response.isSuccessful())
//                            {
//                                data.setValue(response.body());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<NewsResponse> call, Throwable t) {
//                            data.setValue(null);
//                        }
//                    });
//            return data;
//        }
//    }

//    companion object {
//        private var apiService:APIService
//
//        private static class SingletonHelper
//        {
//            private static final HeadLineRepository INSTANCE = new HeadLineRepository();
//        }
//        public static HeadLineRepository getInstance()
//        {
//            return SingletonHelper.INSTANCE;
//        }
//        public HeadLineRepository()
//        {
//            apiService= RetrofitRequest.createService(APIRequest.class);
//        }
//        public LiveData<NewsResponse> getHeadLine(String country,
//        String key)
//        {
//            final MutableLiveData<NewsResponse> data = new MutableLiveData<>();
//            apiService.getHeadLine(country, key)
//                    .enqueue(new Callback<NewsResponse>()
//                    {
//                        @Override
//                        public void onResponse(Call<NewsResponse> call,     Response<NewsResponse> response)
//                        {
//                            if (response.isSuccessful())
//                            {
//                                data.setValue(response.body());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<NewsResponse> call, Throwable t) {
//                            data.setValue(null);
//                        }
//                    });
//            return data;
//        }
//    }

}