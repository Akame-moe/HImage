package sometest;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * Created by gentle-hu on 2018/7/29 9:43.
 * Email:me@gentlehu.com
 */
public class Test {

    public static void main(String[] args) {
        new Test().test_redis();
    }
    public void test_redis(){
        Jedis redis = new Jedis("192.168.193.129");
        System.out.println("connect success.");
        Random r = new Random();
        String no = redis.get("no");
        if(no == null){
            System.out.println("not exist.");
        }else{
            System.out.println("exist:"+no);
        }
        System.out.println(no);
//        for(int i=0;i<10;i++){
//            String token = UUID.randomUUID().toString().substring(0,4);
//            redis.set(token,"user_id_"+i);
//            int ex = r.nextInt(600);
//            redis.expire(token,ex);
//            System.out.println("T:"+token+",U:"+i+",E:"+ex);
//
//        }
    }
}
