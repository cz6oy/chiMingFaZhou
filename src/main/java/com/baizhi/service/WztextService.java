package com.baizhi.service;

import com.baizhi.entity.Wztext;

import java.util.Map;

public interface WztextService {
    Map<String,Object> queryAll(Integer page, Integer rows);
    void addWztext(Wztext wztext);
    void update(Wztext wztext);
    void delete(String[] id);
}
