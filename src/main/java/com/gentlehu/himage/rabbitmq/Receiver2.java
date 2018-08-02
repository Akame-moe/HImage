package com.gentlehu.himage.rabbitmq;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by gentle-hu on 2018/8/2 23:04.
 * Email:me@gentlehu.com
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver2 {

    private Logger logger = Logger.getLogger(Receiver2.class);

    @RabbitHandler
    public void process(String msg){
        logger.info("Receiver2:"+msg);
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
