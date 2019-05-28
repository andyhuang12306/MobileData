package com.example.myapplication.network;

import okhttp3.*;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class RetrofitServiceManager {

    private static final int TIME_OUT=5;
    private static final String BASE_URL = "https://data.gov.sg/api/action/";
    private Retrofit mRetrofit;
    private static final long CACHESIZE=1024*1024*10;
    private static File mCachePath;
    private static Boolean netWorkConnected=false;


    private RetrofitServiceManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        if(mCachePath.isFile()){
            builder.cache(new Cache(mCachePath, CACHESIZE));
        }
        builder.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                String cacheControl = originalResponse.header("Cache-Control");
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + 5000)
                            .build();
                } else {
                    return originalResponse;
                }
            }
        });
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!netWorkConnected) {
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached")
                            .build();
                }
                return chain.proceed(request);

            }
        });
        OkHttpClient client = builder.build();

        mRetrofit=new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static void setNetworkStatus(@Nullable Boolean hasNetwork) {
        netWorkConnected=hasNetwork;
    }

    public static boolean getNetworkStatus() {
        return netWorkConnected;
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE=new RetrofitServiceManager();
    }

    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void setCachPath(File path){
        mCachePath=path;
    }
    public static File getCachPath(){
        return mCachePath;
    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
