package com.gentlehu.himage.service.impl;

import com.gentlehu.himage.mapper.ImageMapper;
import com.gentlehu.himage.pojo.Image;
import com.gentlehu.himage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gentle-hu on 2018/7/29 0:42.
 * Email:me@gentlehu.com
 */
@Transactional
@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Image findById(String id) {
        return imageMapper.findById(id);
    }

    @Override
    public List<Image> findByUid(String uid) {
        return imageMapper.findByUid(uid);
    }

    @Override
    public void insert(Image image) {
        imageMapper.insert(image);
    }

    @Override
    public void update(String id, Image image) {
        imageMapper.update(id,image);
    }

    @Override
    public int count(String column, String value) {
        return imageMapper.count(column,value);
    }

    @Override
    public int count() {
        return imageMapper.count(null,null);
    }
}
