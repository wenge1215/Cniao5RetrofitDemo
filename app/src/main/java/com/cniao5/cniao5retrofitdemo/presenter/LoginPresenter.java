package com.cniao5.cniao5retrofitdemo.presenter;

import android.util.Log;

import com.cniao5.cniao5retrofitdemo.api.CardlanServer;
import com.cniao5.cniao5retrofitdemo.bean.BaseResult;
import com.cniao5.cniao5retrofitdemo.bean.LoginBean;
import com.cniao5.cniao5retrofitdemo.control.CardlanControl;
import com.cniao5.cniao5retrofitdemo.net.HttpLoder;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WENGE on 2017/8/10.
 * 备注：
 */


public class LoginPresenter implements CardlanControl.LoginP {
    private CardlanControl.LoginView mLoginView;
    private CardlanServer mCardlanServer;

    public LoginPresenter(CardlanControl.LoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void login(String name, String pwd) {
        Log.e("login", name + "******" + pwd);

        mCardlanServer = HttpLoder.getHttpLoder().initRetrofit(CardlanServer.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", name);
        map.put("password", "ac8db8de1d7e94ae6cdfa5011f7aadfa");
        map.put("devicename", "android");
        map.put("androidId ", "353515524688244");
        map.put("registrationidandroid", "13065ffa4e397d6bfb6");

        Log.e("hashmap", map.toString());

        String param = new Gson().toJson(map);
        final Call<BaseResult<LoginBean>> login = mCardlanServer.login(param);

        login.enqueue(new Callback<BaseResult<LoginBean>>() {

            @Override
            public void onResponse(Call<BaseResult<LoginBean>> call, Response<BaseResult<LoginBean>> response) {
                Log.e("Callback", "   " + login.equals(call));
                mLoginView.succed(response);
            }

            @Override
            public void onFailure(Call<BaseResult<LoginBean>> call, Throwable t) {
                mLoginView.filed(call, t);
            }
        });
    }

    /**
     * 上传文件
     */
    @Override
    public void upload(File file) {
        if (mCardlanServer == null) {
            mCardlanServer = HttpLoder.getHttpLoder().initRetrofit(CardlanServer.class);
        }

        final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        mCardlanServer.updateFile(filePart).enqueue(new Callback<BaseResult<String>>() {
            @Override
            public void onResponse(Call<BaseResult<String>> call, Response<BaseResult<String>> response) {
                if (200 == response.body().getStatus()) {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("logo", response.body().getData());
                    String s = new Gson().toJson(hashMap);
                    mCardlanServer.updateLogo(s).enqueue(new Callback<BaseResult<String>>() {
                        @Override
                        public void onResponse(Call<BaseResult<String>> call, Response<BaseResult<String>> response) {
                            if (response.body().getStatus() == 200) {
                                mLoginView.upSucceed(response);
                            } else {
                                Log.e("error", response.body().getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResult<String>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<BaseResult<String>> call, Throwable t) {
                mLoginView.upFailure();
            }
        });
    }
}
