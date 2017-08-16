package com.cniao5.cniao5retrofitdemo.control;

import com.cniao5.cniao5retrofitdemo.bean.BaseResult;
import com.cniao5.cniao5retrofitdemo.bean.LoginBean;

import java.io.File;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by WENGE on 2017/8/10.
 * 备注：
 */


public class CardlanControl {
    public interface LoginP{
        void login(String name, String pwd);

        void upload(File file);
    }
    public interface LoginView{
        void succed(Response<BaseResult<LoginBean>> response);
        void filed(Call<BaseResult<LoginBean>> call, Throwable t);

        void upSucceed(Response<BaseResult<String>> response);

        void upFailure();
    }
}
