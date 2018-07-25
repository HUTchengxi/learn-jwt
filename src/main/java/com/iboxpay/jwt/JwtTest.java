package com.iboxpay.jwt;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    private static Key KEY = new SecretKeySpec("javaweb".getBytes(),
            SignatureAlgorithm.HS512.getJcaName());

    public static void main(String[] args) {

        String jwtString = createJwt("1", "1", 1);

        String decodeString = decodeJwt(jwtString);
    }

    public static String createJwt(String id, String subject, long ttlMillis) {

        //指定签名时使用的算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("uid", "DSSFAWDWDAS");
        claims.put("user_name", "admin");
        claims.put("pass_word", "abc123456");



        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        stringObjectMap.put("type", "1");
        String payload = "{\"user_id\":\"1341137\", \"expire_time\":\"2018-01-01 0:00:00\"}";
        String compactJws = Jwts.builder().setHeader(stringObjectMap)
                .setPayload(payload).signWith(SignatureAlgorithm.HS256, KEY).compact();

        System.out.println("jwt key:" + new String(KEY.getEncoded()));
        System.out.println("jwt payload:" + payload);
        System.out.println("jwt encoded:" + compactJws);

        return compactJws;
    }

    public static String decodeJwt(String compactJws) {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(compactJws);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();

        System.out.println("jwt header:" + header);
        System.out.println("jwt body:" + body);
        System.out.println("jwt body user-id:" + body.get("user_id", String.class));

        return compactJws;
    }
}
