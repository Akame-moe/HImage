package sometest.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gentle-hu on 2018/8/2 22:44.
 * Email:me@gentlehu.com
 */

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg){
        this.rabbitTemplate.convertAndSend("hello",msg);
    }
}
