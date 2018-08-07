package sometest.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gentle-hu on 2018/8/3 2:04.
 * Email:me@gentlehu.com
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        System.out.println("create queue...");
        return new Queue("hello");
    }

}
