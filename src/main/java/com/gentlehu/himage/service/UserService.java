package com.gentlehu.himage.service;

import com.gentlehu.himage.pojo.User;

import java.util.List;

/**
 * Created by gentle-hu on 2018/7/29 3:36.
 * Email:me@gentlehu.com
 */
public interface UserService {
    User findByUid(String uid);
    User findByUsername(String username);
    List<User> findByUsernameLike(String username);
    void insert(User user);
    void update(String uid,User user);
    int count(String column,String value);
    int count();
}
