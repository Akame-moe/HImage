package com.gentlehu.himage.service.impl;

import com.gentlehu.himage.mapper.UserMapper;
import com.gentlehu.himage.pojo.User;
import com.gentlehu.himage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gentle-hu on 2018/7/29 3:37.
 * Email:me@gentlehu.com
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUid(String uid) {
        return userMapper.findByUid(uid);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return userMapper.findByUsernameLike(username);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(String uid, User user) {
        userMapper.update(uid,user);
    }

    @Override
    public int count(String column,String value) {
        return userMapper.count(column,value);
    }

    @Override
    public int count() {
        return userMapper.count(null,null);
    }
}
