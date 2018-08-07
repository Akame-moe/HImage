package com.gentlehu.himage.mapper;

import com.gentlehu.himage.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gentle-hu on 2018/7/29 2:29.
 * Email:me@gentlehu.com
 */
@Repository("userMapper")
public interface UserMapper {
    User findByUid(@Param("uid") String uid);
    User findByUsername(@Param("username") String username);
    List<User> findByUsernameLike(@Param("username") String username);
    void insert(@Param("user") User user);
    void update(@Param("uid") String uid,@Param("user") User user);
    int count(@Param("column") String column,@Param("value") String value);
}
