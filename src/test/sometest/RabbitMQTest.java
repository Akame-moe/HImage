package sometest;

import com.gentlehu.himage.Application;
import com.gentlehu.himage.rabbitmq.HelloSender;
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
        try {
            //等待接受者把数据处理完
            Thread.sleep(1000*30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
