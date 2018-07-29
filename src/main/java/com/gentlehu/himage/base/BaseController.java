package com.gentlehu.himage.base;

import com.gentlehu.himage.entity.JsonResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by gentle-hu on 2018/7/29 12:00.
 * Email:me@gentlehu.com
 */
public class BaseController {

    private Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler
    public String exceptionHandler(Exception e){
        logger.error("system error:"+e.toString());
        return JsonResult.error("system error. please ask the admin for help.").toString();
    }
}
