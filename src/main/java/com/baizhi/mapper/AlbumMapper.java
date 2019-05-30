package com.baizhi.mapper;


import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {
    List<Album> queryAll(@Param("page")Integer page, @Param("rows")Integer rows);
    Integer getCount();
    void add(Album album);
    void updateCoverPic(@Param("cover_pic") String cover_pic,@Param("id") String id);
    void update(Album album);
    void delete(@Param("id") String[] id);
}
