package com.emp.configuration;

import com.emp.exceptions.CustomAccessDeniedExceptionResponse;
import com.google.gson.Gson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        CustomAccessDeniedExceptionResponse loginResponse = new CustomAccessDeniedExceptionResponse();
        String jsonLoginResponse = new Gson().toJson(loginResponse);
        System.out.println("jsonLoginResponse "+jsonLoginResponse);
        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().print(jsonLoginResponse);
    }
}
