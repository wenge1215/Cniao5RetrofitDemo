package com.cniao5.cniao5retrofitdemo.bean;

import java.io.Serializable;

/**
 * Created by Wen on 2016/10/7.
 *
 */

public class BaseResult<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T user_id) {
        this.data = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
