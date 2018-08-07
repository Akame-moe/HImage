package sometest;

import com.gentlehu.himage.Application;
import sometest.rabbitmq.HelloSender;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by gentle-hu on 2018/8/2 23:07.
 * Email:me@gentlehu.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitMQTest {

    @Autowired
    private HelloSender helloSender;

    @org.junit.Test
    public void test(){
        for(int i=0;i<10;i++){
            helloSender.send("msg:"+i*10);
        }
        System.out.println("sender done.");
    }

}
