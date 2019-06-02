package com.baizhi.service;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.DelCache;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer count = userMapper.getCount();
        Integer start = (page-1)*rows;
        Integer total = count%rows==0?count/rows:count/rows+1;
        List<User> users = userMapper.queryAll(start, rows);
        map.put("page",page);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",users);
        return map;
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    @AddCache
    public List<User> queryAllUser() {
        List<User> userList = userMapper.queryAllUser();
        return userList;
    }

    @Override
    @AddCache
    public User findById(String id) {
        User user = userMapper.findById(id);
        return user;
    }

    @Override
    @DelCache
    public void delete(String[] id) {
        userMapper.delete(id);
    }
}
