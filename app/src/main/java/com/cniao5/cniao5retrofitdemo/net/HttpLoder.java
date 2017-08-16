package com.cniao5.cniao5retrofitdemo.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WENGE on 2017/8/10.
 * 备注：
 */


public class HttpLoder {
    public static HttpLoder sHttpLoder;
    public static HttpLoder getHttpLoder() {
        if (sHttpLoder == null) {
            synchronized (HttpLoder.class) {
                if (sHttpLoder == null) {
                    sHttpLoder = new HttpLoder();
                }
            }
        }
        return sHttpLoder;
    }

    public <T> T initRetrofit(Class<T> clz){
        /**
         * 用于输出网络请求和结果的 Log
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /**
         * 设置token拦截器
         */
        Interceptor mTokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MDMwNDI4NTcsInBob25lIjoiMTc2ODg3NTc5NjMiLCJ1c2VyaWQiOjY3fQ.3nGj67McZh7KttBRrWLfra_x9yrMuNjVA1F2AhM9tQE";
                Request originalRequest = chain.request();
                if (token == null) {
                    return chain.proceed(originalRequest);
                }
                Request authorised = originalRequest.newBuilder()
                        .header("CARDLAN_AUTH", token)
                        .build();
                return chain.proceed(authorised);
            }
        };


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(mTokenInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://cloud.cardlan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clz);
    }

    public <T> T initRetrofit(String baseUrl,Class<T> clz){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clz);
    }




}
