package com.cniao5.cniao5retrofitdemo.bean;

import java.io.Serializable;

/**
 * 描述：
 * Created by enjoy on 2017/4/11.
 */

public class LoginBean implements Serializable {

    /**
     * logo : null
     * sex : null
     * nickname : null
     * account : amy
     * phone : amy
     * balance : 89.5
     * token : 8d25f82a65ad87b2be4b7cf7435e6110
     */
    private String logo;
    private String sex;
    private String nickname;
    private String phone;
    private double balance;
    private String token;
    private int userid;
    private int isopen;//0 设置支付密码 1 修改支付密码

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getIsopen() {
        return isopen;
    }

    public void setIsopen(int isopen) {
        this.isopen = isopen;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "logo='" + logo + '\'' +
                ", sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                ", token='" + token + '\'' +
                ", userid='" + userid + '\'' +
                ", isopen='" + isopen + '\'' +
                '}';
    }
}




