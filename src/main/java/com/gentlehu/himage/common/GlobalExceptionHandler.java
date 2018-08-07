package com.gentlehu.himage.common;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gentle-hu on 2018/8/2 19:24.
 * Email:me@gentlehu.com
 */

@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        logger.error(String.format("catch global exception:%s from handler:%s.",e,handler));
        try {
            response.getOutputStream().println("ErrorCode:500#service unavailable now, please ask the administrator <admin@gentlehu.com> for help.");
        } catch (IOException ignored) {
        }
        return new ModelAndView();
    }
}
