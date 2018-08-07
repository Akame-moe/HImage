package sometest.rabbitmq;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by gentle-hu on 2018/8/2 22:59.
 * Email:me@gentlehu.com
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver1 {

    private Logger logger = Logger.getLogger(Receiver1.class);

    @RabbitHandler
    public void process(String msg){
        logger.info("Receiver1:"+msg);
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
