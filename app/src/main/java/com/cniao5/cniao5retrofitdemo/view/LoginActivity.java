package com.cniao5.cniao5retrofitdemo.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cniao5.cniao5retrofitdemo.R;
import com.cniao5.cniao5retrofitdemo.bean.BaseResult;
import com.cniao5.cniao5retrofitdemo.bean.LoginBean;
import com.cniao5.cniao5retrofitdemo.control.CardlanControl;
import com.cniao5.cniao5retrofitdemo.permission.PermissionReq;
import com.cniao5.cniao5retrofitdemo.permission.PermissionResult;
import com.cniao5.cniao5retrofitdemo.permission.Permissions;
import com.cniao5.cniao5retrofitdemo.presenter.LoginPresenter;
import com.cniao5.cniao5retrofitdemo.utils.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements CardlanControl.LoginView {

    CardlanControl.LoginP mLoginP;
    private EditText mEtPwd;
    private String mPhotoPath;
    private File mFile;

    @Override
    public void succed(Response<BaseResult<LoginBean>> response) {
//        Toast.makeText(this, "succeed    " +response.body().getData().getNickname(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "succeed" + response.raw().code() + "*****" + response.body().getStatus() + "msg:  " + response.body().getMsg(), Toast.LENGTH_SHORT).show();
//        response.raw().code();
    }

    private EditText mEtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mLoginP = new LoginPresenter(this);
    }

    public void btnClick(View v) {
        Util.hideKeyboard(this, mEtName);
        String name = mEtName.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        //校验
        //登录
        login(name, pwd);
    }


    public void btnUpload(View view) {
        savePhoto();
        if (isSucc) {
            //上传图片
            mLoginP.upload(mFile);
        }
    }

    private boolean isSucc = false;

    private void savePhoto() {

        //请求权限
        PermissionReq.with(this).permissions(Permissions.STORAGE).result(new PermissionResult() {
            @Override
            public void onGranted() {
                String str = "";
                if (TextUtils.isEmpty(mPhotoPath)) {
                    saveCard();
                    str = "保存成功";
                } else {
                    str = "内容已经存在";
                }
                Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
                isSucc = true;
            }

            @Override
            public void onDenied() {
                isSucc = false;
            }
        }).request();
    }



    private void saveCard() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.erha);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        mPhotoPath = path + "/erha" + ".png";
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        FileOutputStream out = null;
        try {
            mFile = new File(mPhotoPath);
            if (!mFile.exists()) {
                out = new FileOutputStream(mFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            } else {
                Toast.makeText(this, "文件已存在", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void login(String name, String pwd) {
        mLoginP.login(name, pwd);
    }

    @Override
    public void filed(Call<BaseResult<LoginBean>> call, Throwable t) {
        Toast.makeText(this, "filed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void upSucceed(Response<BaseResult<String>> response) {
        Toast.makeText(this, "upSucceed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void upFailure() {
        Toast.makeText(this, "upFailure", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionReq.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
