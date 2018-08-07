package com.gentlehu.himage.utils;

import com.gentlehu.himage.Config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by gentle-hu on 2018/7/29 11:51.
 * Email:me@gentlehu.com
 */
public class HttpUtil {

    public static String fetchToken(HttpServletRequest request){
        String token = request.getHeader(Config.TOKEN_NAME);
        Cookie[] cookies = request.getCookies();
        if(TextUtil.isEmpty(token) && cookies != null){
            for (Cookie cookie : cookies) {
                if(Config.TOKEN_NAME.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return token;
    }
}
