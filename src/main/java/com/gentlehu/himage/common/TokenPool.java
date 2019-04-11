package com.gentlehu.himage.common;

import com.gentlehu.himage.Config;
import com.gentlehu.himage.pojo.User;
import com.gentlehu.himage.utils.MD5Util;
import redis.clients.jedis.Jedis;

/**
 * Created by gentle-hu on 2018/7/29 10:58.
 * Email:me@gentlehu.com
 */
public class TokenPool {
    private static final int EXPIRE_SECOND = 3600;//60min

    private static final String SALT = "gentlehu";

    private static Jedis redis;

    /**
     * 获取当前redis实例，只有连接为空的时候才去调用连接方法
     */
    private static Jedis getRedis(){
        if(redis != null){
            return redis;
        }else{
            redis = connect(Config.REDIS_HOST,Config.REDIS_PORT);
            return redis;
        }
    }

    /**
     * 断开当前连接，创建新连接
     */
    public static Jedis connect(String host,int port){
        if(redis != null && redis.isConnected()){
            redis.disconnect();
            redis = null;
        }
        return new Jedis(host,port);
    }

    public static String get(String token){
        return getRedis().get(token);
    }

    public static void put(String token,String userId){
        getRedis().set(token,userId);
        getRedis().expire(token,EXPIRE_SECOND);
    }


    public static boolean exist(String token){
        return getRedis().exists(token);
    }

    public static void update(String token){
        getRedis().expire(token,EXPIRE_SECOND);
    }

    public static void remove(String token){
        getRedis().del(token);
    }

    public static long ttl(String token){
        return getRedis().ttl(token);
    }

    public static void clear(){
        getRedis().flushDB();
    }

    public static String generateToken(User user){
        return MD5Util.digest(user.getUid() + System.currentTimeMillis() + SALT);
    }


}
