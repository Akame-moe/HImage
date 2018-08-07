package com.gentlehu.himage;

/**
 * Created by gentle-hu on 2018/7/29 11:26.
 * Email:me@gentlehu.com
 */
public class Config {
    public static final String TOKEN_NAME = "_token";
    public static final int COOKIE_MAX_AGE = 30 * 24 * 3600;


    public static final String UPLOAD_TEMP_FOLDER = "F:/nothing/temp";
    public static final String UPLOAD_MAX_FILE_SIZE = "5MB";
    public static final String UPLOAD_MAX_REQUEST_SIZE = "5MB";

    public static final String REDIS_HOST = "192.168.193.129";
    public static final int REDIS_PORT = 6379;

    public static final String STATIC_IMAGE_SERVER = "http://127.0.0.1:8081";//nginx
}
