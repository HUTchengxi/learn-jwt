package com.iboxpay.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkToken")
public class CheckToken extends HttpServlet {

    public CheckToken() {
        super();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        Cookie[] cookies = request.getCookies();
        String encToken = "";
        for(Cookie cookie: cookies) {
            String cookieName = cookie.getName();
            if("token".equals(cookieName)) {
                encToken = cookie.getValue();
                break;
            }
        }

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(Constant.LOGIN_KEY)
                .parseClaimsJws(encToken);

        JwsHeader header = claimsJws.getHeader();

        Claims payLoader = claimsJws.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("t_header", header);
        resultMap.put("t_payLoader", payLoader);
        resultMap.put("t_signature", "");
        String result = objectMapper.writeValueAsString(resultMap);

        writer.print(result);
        writer.close();
    }
}
