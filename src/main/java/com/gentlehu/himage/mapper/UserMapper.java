package com.gentlehu.himage.mapper;

import com.gentlehu.himage.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gentle-hu on 2018/7/29 2:29.
 * Email:me@gentlehu.com
 */
@Repository("userMapper")
public interface UserMapper {
    User findByUid(String uid);
    User findByUsername(String username);
    List<User> findByUsernameLike(String username);
    void insert(User user);
    void update(String uid,User user);
    int count(String column,String value);
}
