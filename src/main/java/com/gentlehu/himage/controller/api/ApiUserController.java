package com.gentlehu.himage.controller.api;

import com.gentlehu.himage.Config;
import com.gentlehu.himage.common.TokenPool;
import com.gentlehu.himage.entity.JsonResult;
import com.gentlehu.himage.pojo.User;
import com.gentlehu.himage.service.UserService;
import com.gentlehu.himage.utils.HttpUtil;
import com.gentlehu.himage.utils.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gentle-hu on 2018/7/29 10:35.
 * Email:me@gentlehu.com
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/register",method = RequestMethod.POST)
    public JsonResult register(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email){
        User user = new User();
        String uid = TextUtil.generateUID();
        user.setUid(uid);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userService.insert(user);
        return JsonResult.ok();
    }

    @RequestMapping(value = "/api/user/login",method = RequestMethod.POST)
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password, HttpServletResponse response){
        User user = userService.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            String token = TokenPool.generateToken(user);
            TokenPool.put(token,user.getUid());
            Cookie cookie = new Cookie(Config.TOKEN_NAME, token);
            cookie.setPath("/");
            cookie.setMaxAge(Config.COOKIE_MAX_AGE);
            response.addCookie(cookie);
            return JsonResult.ok();
        }
        return JsonResult.error("user not found.");
    }



    @RequestMapping(value = "/api/user/online",method = RequestMethod.POST)
    public JsonResult info(HttpServletRequest request){
        String token = HttpUtil.fetchToken(request);
        if(TextUtil.isNotEmpty(token)){
            String uid = TokenPool.get(token);
            if(TextUtil.isNotEmpty(uid)){
                User user = userService.findByUid(uid);
                user.setPassword(null);
                return JsonResult.ok(user);
            }
        }
        return JsonResult.error("not online.");
    }


}
