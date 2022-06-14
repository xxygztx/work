package com.power.setting.interceptor;


import com.power.setting.utills.TwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //将token进行解密
        String result = TwtUtil.verifyToken(token);
        if(null == result || "".equals(result)){
            response.sendRedirect("/toLogin");
            return false;
        }
        return true;
    }
}
