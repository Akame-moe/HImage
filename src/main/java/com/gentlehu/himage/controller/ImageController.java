package com.gentlehu.himage.controller;

import com.gentlehu.himage.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gentle-hu on 2018/7/28 22:10.
 * Email:me@gentlehu.com
 */

@Controller
public class ImageController extends BaseController {

    @RequestMapping(value = {"/index","/"})
    public String index(){
        return "/static/index.html";
    }
}
