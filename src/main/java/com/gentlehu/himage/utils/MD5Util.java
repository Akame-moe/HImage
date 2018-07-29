package com.gentlehu.himage.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gentle-hu on 2018/7/29 11:02.
 * Email:me@gentlehu.com
 */
public class MD5Util {
    public static String digest(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return byte2hex(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            //can't happened.
        }
        return "";
    }
    private static String byte2hex(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        String temp;
        for (int i = 0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if(temp.length() == 1){
                sb.append("0");//不足两位补0
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
