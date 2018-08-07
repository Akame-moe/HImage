package com.gentlehu.himage.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gentle-hu on 2018/7/29 11:02.
 * Email:me@gentlehu.com
 */
public class MD5Util {
    private static final char[] hexDigits;
    static {
        hexDigits = "0123456789abcdef".toCharArray();
    }

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
        StringBuilder sb = new StringBuilder(bytes.length*2);
        for (int i = 0;i<bytes.length;i++){
            sb.append(hexDigits[(bytes[i] >> 4) & 0xF]);
            sb.append(hexDigits[bytes[i] & 0xF]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.digest("alisa"));
    }
}
