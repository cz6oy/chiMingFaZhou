package com.baizhi.mapper;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> queryAll(@Param("page")Integer page,@Param("rows")Integer rows);
    Integer getCount();
    void update(User user);
    List<User> queryAllUser();
    User findById(String id);
    void delete(@Param("id") String[] id);
}
