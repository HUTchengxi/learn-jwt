package com.iboxpay.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

    private final static long serialVersionUID = 1L;

    public Login(){
        super();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        String username = request.getParameter("login_name");
        String password=  request.getParameter("login_pass");

        System.out.println(username + "\n" + password);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "jwt");
        header.put("alg", "HS256");

        Token token = new Token(username);
        ObjectMapper objectMapper = new ObjectMapper();
        String payLoader = objectMapper.writeValueAsString(token);

        SignatureAlgorithm signature = SignatureAlgorithm.HS256;

        String encToken = Jwts.builder()
                .setHeader(header)
                .setPayload(payLoader)
                .signWith(signature, Constant.LOGIN_KEY)
                .compact();

        System.out.println("your token: " + encToken);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("token", encToken);
        resultMap.put("status", Constant.STATUS_SUCCESS);
        resultMap.put("forwardUrl", Constant.LOGIN_SUCCESS_URL);
        String result = objectMapper.writeValueAsString(resultMap);

        System.out.println("return result: " + result);

        writer.print(result);
        writer.close();
    }
}
