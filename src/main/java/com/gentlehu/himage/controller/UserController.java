package com.gentlehu.himage.controller;

import com.gentlehu.himage.Config;
import com.gentlehu.himage.annotation.Authorized;
import com.gentlehu.himage.common.TokenPool;
import com.gentlehu.himage.utils.HttpUtil;
import com.gentlehu.himage.utils.TextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gentle-hu on 2018/7/29 11:59.
 * Email:me@gentlehu.com
 */
@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(){
        return "/static/login.html";
    }

    @RequestMapping("/register")
    public String register(){
        return "/static/register.html";
    }

    @Authorized
    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response){
        String token = HttpUtil.fetchToken(request);
        if(TextUtil.isNotEmpty(token)){
            TokenPool.remove(token);
            //delete cookie of client side
            Cookie cookie = new Cookie(Config.TOKEN_NAME, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
        }
    }

}
