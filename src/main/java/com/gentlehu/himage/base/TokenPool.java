package com.gentlehu.himage.base;

import com.gentlehu.himage.pojo.User;
import com.gentlehu.himage.utils.MD5Util;

import java.util.*;

/**
 * Created by gentle-hu on 2018/7/29 10:58.
 * Email:me@gentlehu.com
 */
public class TokenPool {
    private static final long DELAY = 60 * 60 * 1000;//60min
    private static final long PERIOD = 30 * 60 * 1000;//30min
    private static final String SALT = "gentlehu";

    public static class UserInfo{
        public String uid;
        public String username;
        public long expireTime;

        public UserInfo(String uid, String username) {
            this.uid = uid;
            this.username = username;
            this.expireTime = System.currentTimeMillis() + DELAY;
        }
    }

    //目前用hash map 存储，日后考虑用redis/memcached等数据库
    private static final Map<String,UserInfo> map = new HashMap<>();


    private static Timer timer = null;

    private static boolean isRunning = false;

    private static class ClearTask extends TimerTask {

        @Override
        public void run() {
            Set<String> set = map.keySet();
            long now = System.currentTimeMillis();
            for (String token : set) {
                UserInfo record = map.get(token);
                if(record.expireTime < now){
                    map.remove(token);
                }
            }

        }
    }

    public static UserInfo get(String token){
        return map.get(token);
    }

    private static void put(String token,UserInfo user){
        map.put(token,user);
        if(!isRunning){
            startTask();
        }
    }

    public static void put(String token,User user){
        put(token,new UserInfo(user.getUid(),user.getUsername()));
    }

    public static boolean exist(String token){
        UserInfo record = map.get(token);
        return record != null && record.expireTime > System.currentTimeMillis();
    }

    public static boolean update(String token){
        UserInfo userInfo = map.get(token);
        if(userInfo != null){
            userInfo.expireTime = System.currentTimeMillis() + DELAY;
            return true;
        }
        return false;
    }

    public static void remove(String token){
        map.remove(token);
        if(map.isEmpty()){
            stopTask();
        }
    }

    public static void clear(){
        map.clear();
        stopTask();
    }

    private static void stopTask(){
        timer.cancel();
        timer = null;
        isRunning = false;
    }

    private static void startTask(){
        timer = new Timer();
        timer.schedule(new ClearTask(),0,PERIOD);
        isRunning = true;
    }

    public static String generateToken(User user){
        String token = MD5Util.digest(user.getUid() + System.currentTimeMillis() + SALT);
        return token;
    }


}
