package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BannerService {
    Map<String,Object> queryAll(Integer page, Integer rows);
    String add(String title,String img_pic,String status,String description);
    void updateHeadPic(String img_url,String id);
    void delete(String[] id);
    String update(String id, String title, String status, Date create_date, String description);
}
