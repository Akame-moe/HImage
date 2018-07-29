package com.gentlehu.himage.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by gentle-hu on 2018/7/29 2:08.
 * Email:me@gentlehu.com
 */
@Data
public class User {
    private String uid;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private int status;

}
