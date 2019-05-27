package com.example.myapplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitServiceManager {

    private static final int TIME_OUT=5;
    private Retrofit mRetrofit;

    private RetrofitServiceManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        mRetrofit=new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE=new RetrofitServiceManager();
    }

    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
