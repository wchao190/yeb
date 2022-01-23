package com.xxx.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.server.pojo.ResultOV;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未登录 或 token 失效，自定义返回结果
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResultOV error = ResultOV.error("未登录，请登录");
        error.setCode(401);
        //创建字符流
        PrintWriter writer = response.getWriter();
        //将 JavaBean 对象转成 json 字符串输出
        writer.write(new ObjectMapper().writeValueAsString(error));
        writer.flush();
        writer.close();
    }
}
