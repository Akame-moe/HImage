package com.gentlehu.himage.controller.api;

import com.gentlehu.himage.Config;
import com.gentlehu.himage.common.BaseController;
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
import java.util.UUID;

/**
 * Created by gentle-hu on 2018/7/29 10:35.
 * Email:me@gentlehu.com
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiUserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/register",method = RequestMethod.POST)
    public JsonResult register(String username,String password,String email){
        User user = new User();
        int hashCode = UUID.randomUUID().hashCode();
        user.setUid(String.valueOf(hashCode));
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userService.insert(user);
        return JsonResult.ok();
    }

    @RequestMapping(value = "/api/user/login",method = RequestMethod.POST)
    public JsonResult login(@RequestParam("username") String username, String password, HttpServletResponse response){
        User user = userService.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            String token = TokenPool.generateToken(user);
            TokenPool.put(token,user.getUid());
            response.addCookie(new Cookie(Config.TOKEN_NAME,token));
            return JsonResult.ok();
        }
        return JsonResult.error("user not found.");
    }

    @RequestMapping(value = "/api/user/logout",method = RequestMethod.GET)
    public JsonResult logout(HttpServletRequest request){
        String token = HttpUtil.fetchToken(request);
        if(!TextUtil.isEmpty(token)){
            TokenPool.remove(token);
        }
        return JsonResult.ok();
    }
}
