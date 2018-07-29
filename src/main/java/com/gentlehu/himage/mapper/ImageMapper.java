package com.gentlehu.himage.mapper;

import com.gentlehu.himage.pojo.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gentle-hu on 2018/7/29 0:48.
 * Email:me@gentlehu.com
 */
@Repository("imageMapper")
public interface ImageMapper {
    Image findById(String id);
    List<Image> findByUid(String uid);
    void insert(Image image);
    void update(String id,Image image);
    int count(String column,String value);
}
