package com.iboxpay.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public interface Constant {

    Integer STATUS_SUCCESS = 1;

    Integer STATUS_FAILURE = 0;

    String LOGIN_SUCCESS_URL = "success.html";

    Key LOGIN_KEY = new SecretKeySpec("chengxi".getBytes(), SignatureAlgorithm.HS256.getJcaName());

}
