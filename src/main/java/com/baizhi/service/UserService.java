package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> queryAll(Integer page, Integer rows);
    void update(User user);
    List<User> queryAllUser();
    User findById(String id);
    void delete(String[] id);
}
