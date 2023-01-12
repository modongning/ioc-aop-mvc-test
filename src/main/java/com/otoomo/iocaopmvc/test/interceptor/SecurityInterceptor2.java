package com.otoomo.iocaopmvc.test.interceptor;

import com.otoomo.web.annotation.Interceptor;
import com.otoomo.web.interceptor.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Interceptor(
        interceptUri = {"/account/getAllAccountInfo"}
)
public class SecurityInterceptor2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Method method) {
        System.out.println("SecurityInterceptor2 preHandle......");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Method method) {
        System.out.println("SecurityInterceptor2 postHandle......");
    }
}
