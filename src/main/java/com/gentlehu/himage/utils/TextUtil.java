package com.gentlehu.himage.utils;

import java.time.LocalDate;
import java.util.Random;

/**
 * Created by gentle-hu on 2018/7/29 8:07.
 * Email:me@gentlehu.com
 */
public class TextUtil {
    public static final String LINE_SEPERATOR = System.getProperty("line.separator", "\n");

    public static boolean isEmpty(String str){
        return str == null || str.trim().equals("");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String suffix(String fileName){
        int i = fileName.lastIndexOf(".");
        try {
            return fileName.substring(i, fileName.length());
        } catch (Exception e) {
            return "";
        }
    }

    private static final String UID_TEMPLATE = "%04d%4d%02d%02d";//random int + year + month + day

    public static String generateUID(){
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int i = random.nextInt(9999);
        return String.format(UID_TEMPLATE,i,now.getYear(),now.getMonthValue(),now.getDayOfMonth());
    }
}
