package com.cniao5.cniao5retrofitdemo.api;

import com.cniao5.cniao5retrofitdemo.bean.BaseResult;
import com.cniao5.cniao5retrofitdemo.bean.LoginBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by WENGE on 2017/8/10.
 * 备注：
 */


public interface CardlanServer {
    @FormUrlEncoded
    @POST("appUserApi/appLogin")
    Call<BaseResult<LoginBean>> login(@Field("param") String params);


    @Multipart
    @POST("upLoadController/file")
    Call<BaseResult<String>> updateFile(@Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("appUserApi/updateAppUser")
    Call<BaseResult<String>> updateLogo( @Field("param") String param);

}
