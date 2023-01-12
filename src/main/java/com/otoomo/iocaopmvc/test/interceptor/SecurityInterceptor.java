package com.otoomo.iocaopmvc.test.interceptor;

import com.otoomo.iocaopmvc.test.annotation.Security;
import com.otoomo.web.annotation.Interceptor;
import com.otoomo.web.interceptor.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Interceptor(
        interceptUri = {"/account/.*"}
)
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Method method) {
        System.out.println("SecurityInterceptor preHandle......");
        if (!method.isAnnotationPresent(Security.class)) {
            return true;
        }
        Security security = method.getAnnotation(Security.class);
        String[] authUserNames = security.userName();

        String userName = request.getParameter("userName");
        if (null == userName || userName.length() == 0) {
            response.setStatus(403);
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().print("userName错误");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //验证权限
        for (String authUserName : authUserNames) {
            if (authUserName.equals(userName)) {
                return true;
            }
        }
        response.setStatus(401);
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().print("[" + userName + "]没有权限");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Method method) {
        System.out.println("SecurityInterceptor postHandle......");
    }
}
