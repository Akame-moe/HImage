package com.gentlehu.himage.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by gentle-hu on 2018/8/8 2:21.
 * Email:me@gentlehu.com
 */
public class HttpCodeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        int status = response.getStatus();
        //if status code is 4xx or 5xx , rewrite the response.
        if(status >= 400){
            response.reset();
            response.setStatus(status);
            response.setHeader("Server","Akame-moe's server");
            PrintWriter writer = response.getWriter();
            writer.write("error-code:"+status);
            writer.flush();
            writer.close();
        }
    }
}
