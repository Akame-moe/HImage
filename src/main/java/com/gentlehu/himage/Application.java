package com.gentlehu.himage;

import com.gentlehu.himage.interceptor.AuthorizedInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * Created by gentle-hu on 2018/7/28 23:58.
 * Email:me@gentlehu.com
 */

//@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.gentlehu.himage.mapper")
public class Application{


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //这里设置的是文件的临时目录，必须提前存在且是绝对路径
        factory.setLocation("F:/nothing/temp");
        factory.setMaxFileSize("5MB");
        factory.setMaxRequestSize("5MB");
        return factory.createMultipartConfig();
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                super.addInterceptors(registry);
                registry.addInterceptor(new AuthorizedInterceptor());
            }
        };
    }


}
