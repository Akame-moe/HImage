package com.gentlehu.himage.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by gentle-hu on 2018/7/29 2:09.
 * Email:me@gentlehu.com
 */
@Data
public class Image {
    private Integer id;
    private String uid;
    private String relpath;
    private String location;
    private LocalDateTime createTime;
    private int status;

}
