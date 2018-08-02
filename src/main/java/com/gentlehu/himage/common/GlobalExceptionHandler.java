package com.gentlehu.himage.common;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gentle-hu on 2018/8/2 19:24.
 * Email:me@gentlehu.com
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        //TODO: send an email.
        logger.error(String.format("catch global exception:%s from handler:%s.",e,handler));
        try {
            response.sendError(500,"server unavailable now,please ask admin <admin@gentlehu.com> for help.");
        } catch (IOException e1) {
        }
        return null;
    }
}
