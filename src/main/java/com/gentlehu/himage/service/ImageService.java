package com.gentlehu.himage.service;

import com.gentlehu.himage.pojo.Image;

import java.util.List;

/**
 * Created by gentle-hu on 2018/7/29 0:42.
 * Email:me@gentlehu.com
 */
public interface ImageService {
    Image findById(int id);
    List<Image> findByUid(String uid);
    void insert(Image image);
    void update(Integer id,Image image);
    int count(String column,String value);
    int count();
}
