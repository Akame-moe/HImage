package com.gentlehu.himage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gentle-hu on 2018/7/28 22:10.
 * Email:me@gentlehu.com
 */

@Controller
public class ImageController {

    @RequestMapping(value = {"/index","/"})
    public String index(){
        return "/static/index.html";
    }

    @RequestMapping("/image/history")
    public String history(){
        return "/static/history.html";
    }

}
