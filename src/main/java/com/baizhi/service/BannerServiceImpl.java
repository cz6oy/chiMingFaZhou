package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Map<String,Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer start = (page-1)*rows;

        //jqgrid  map   page当前的页数  total总页数    records总条数  rows查询到的数据
        List<Banner> list = bannerMapper.queryAll(start,rows);
        Integer count = bannerMapper.getCount();
        Integer total = count%rows==0?count/rows:(count/rows)+1;
        map.put("page",page);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",list);
        return map;
    }

    @Override
    public String add(String title, String img_pic, String status, String description) {
        String id = UUID.randomUUID().toString();
        Banner banner = new Banner();
        banner.setId(id);
        banner.setTitle(title);
        banner.setImg_pic(img_pic);
        banner.setStatus(status);
        banner.setCreate_date(new Date());
        banner.setDescription(description);
        bannerMapper.add(banner);
        return id;
    }

    @Override
    public void updateHeadPic(String img_url,String id) {
        bannerMapper.updateHeadPic(img_url,id);
    }

    @Override
    public void delete(String[] id) {
        bannerMapper.delete(id);
    }

    @Override
    public String update(String id, String title, String status, Date create_date, String description) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setTitle(title);
        banner.setStatus(status);
        banner.setCreate_date(new Date());
        banner.setDescription(description);
        bannerMapper.update(banner);
        return id;
    }
}
