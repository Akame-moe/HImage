package com.gentlehu.himage.interceptor;

import com.gentlehu.himage.Config;
import com.gentlehu.himage.annotation.Authorized;
import com.gentlehu.himage.base.TokenPool;
import com.gentlehu.himage.utils.TextUtil;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gentle-hu on 2018/7/29 11:15.
 * Email:me@gentlehu.com
 */
public class AuthorizedInterceptor implements HandlerInterceptor {

    private static Logger logger = Logger.getLogger(AuthorizedInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> controller = handlerMethod.getBean().getClass();
        Authorized annotation = handlerMethod.getMethodAnnotation(Authorized.class);
        if(annotation != null || controller.isAnnotationPresent(Authorized.class)){//如果方法上有授权注解或者controller上有授权注解
            String uri = request.getRequestURI();
            logger.info("found @Authorized url:" + uri);
            //先在header中找token,没有的话就在cookie中找
            String token = request.getHeader(Config.TOKEN_NAME);
            Cookie[] cookies = request.getCookies();
            if(TextUtil.isEmpty(token) && cookies != null){
                for (Cookie cookie : cookies) {
                    if(Config.TOKEN_NAME.equals(cookie.getName())){
                        token = cookie.getValue();
                        break;
                    }
                }
            }
            if (!TextUtil.isEmpty(token) && TokenPool.exist(token)) {
                //已经登录的用户每访问一次需要授权的接口刷新一次token过期时间
                TokenPool.update(token);
                return true;
            }
            //如果以api开头则请求的是json数据，否则是页面请求
            if(uri.startsWith("/api")){
                logger.info("response sendError 'unauthorized for this api'");
                response.sendError(403,"unauthorized for this api.");
            }else{
                logger.info("response sendRedirect to /admin/login");
                response.sendRedirect("/admin/login");
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
