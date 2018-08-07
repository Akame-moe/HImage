package com.gentlehu.himage.common;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * this function is the same as GlobalExceptionHandler,but only for api controller.
 *
 * Created by gentle-hu on 2018/7/29 12:00.
 * Email:me@gentlehu.com
 */
//@ControllerAdvice(basePackages = {"com.gentlehu.himage.controller.api"})
public class UnknownExceptionHandler {

    private Logger logger = Logger.getLogger(UnknownExceptionHandler.class);


    @ResponseBody
    @ExceptionHandler
    public String exceptionHandler(Exception e){
        logger.error("BaseController@exceptionHandler$error:"+e.toString());
        return "[UnknownException]->please try again later.";
    }
}
