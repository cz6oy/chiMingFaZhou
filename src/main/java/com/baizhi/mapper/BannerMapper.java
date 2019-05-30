package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BannerMapper {
    List<Banner> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);
    Integer getCount();
//    void add(@Param("id") String id, @Param("title") String title, @Param("img_pic") String img_pic, @Param("status") String status, @Param("create_date")Date create_date, @Param("description") String description);
    void add(Banner banner);
    void updateHeadPic(@Param("img_url") String img_url,@Param("id") String id);
    void delete(@Param("id") String[] id);
    void update(Banner banner);
}
