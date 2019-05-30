package com.baizhi.service;

import java.util.Date;
import java.util.Map;

public interface AlbumService {
    Map<String,Object> queryAll(Integer page,Integer rows);
    String add(String title, Integer score, String author, String broadcast, Integer count, String brief, Date create_date, String cover_pic);
    void updateCoverPic(String cover_pic,String id);
    String update(String id,String title, Integer score, String author, String broadcast, Integer count, String brief, Date create_date, String cover_pic);
    void delete(String[] id);
}