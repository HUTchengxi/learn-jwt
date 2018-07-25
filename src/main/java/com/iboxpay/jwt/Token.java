package com.iboxpay.jwt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Token implements Serializable {

    private String ttl;

    private String login_name;

    private final static Long serialVersionUID = 1L;

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Token() {

    }

    public Token(String login_name) {

        this.login_name = login_name;
        Date now = new Date();
        ttl = simpleDateFormat.format(now);
    }

    public Token(String login_name, String ttl) {

        this.login_name = login_name;
        this.ttl = ttl;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "User{" +
                "login_name='" + login_name + '\'' +
                ", ttl='" + ttl + '\'' +
                '}';
    }
}
