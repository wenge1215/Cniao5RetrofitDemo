package com.cniao5.cniao5retrofitdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by WENGE on 2017/8/10.
 * 备注：
 */


public class Util {
    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager)activity. getSystemService(Context.INPUT_METHOD_SERVICE);
//
//// 获取软键盘的显示状态
//        boolean isOpen=imm.isActive();
//
//// 如果软键盘已经显示，则隐藏，反之则显示
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//
//
//
//// 强制显示软键盘
//        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);

// 强制隐藏软键盘
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
