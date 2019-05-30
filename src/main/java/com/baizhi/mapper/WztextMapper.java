package com.baizhi.mapper;

import com.baizhi.entity.Wztext;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WztextMapper {
    List<Wztext> queryAll(@Param("page")Integer page, @Param("rows")Integer rows);
    Integer getCount();
    void addWztext(Wztext wztext);
    void update(Wztext wztext);
    void delete(@Param("id") String[] id);
}
