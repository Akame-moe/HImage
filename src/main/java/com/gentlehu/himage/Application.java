package com.gentlehu.himage;

import com.gentlehu.himage.common.GlobalExceptionHandler;
import com.gentlehu.himage.common.JsonResultHttpMessageConverter;
import com.gentlehu.himage.interceptor.AuthorizedInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.List;

/**
 * Created by gentle-hu on 2018/7/28 23:58.
 * Email:me@gentlehu.com
 */

@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class, ThymeleafAutoConfiguration.class})
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
        factory.setLocation(Config.UPLOAD_TEMP_FOLDER);
        factory.setMaxFileSize(Config.UPLOAD_MAX_FILE_SIZE);
        factory.setMaxRequestSize(Config.UPLOAD_MAX_REQUEST_SIZE);
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

            @Override
            public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
                super.configureHandlerExceptionResolvers(exceptionResolvers);
                exceptionResolvers.add(new GlobalExceptionHandler());
            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                super.configureMessageConverters(converters);
                converters.add(new JsonResultHttpMessageConverter());
            }
        };
    }

    //显示所有spring配置的bean名称
    //SEE:https://spring.io/guides/gs/spring-boot/
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
        };
    }




}
