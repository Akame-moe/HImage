package com.gentlehu.himage.utils;

import com.google.gson.Gson;

/**
 * Created by gentle-hu on 2018/7/29 3:59.
 * Email:me@gentlehu.com
 */
public class GsonUtil {

    private static final Gson gson = new Gson();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }
}
