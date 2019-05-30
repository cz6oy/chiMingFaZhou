package com.baizhi.mapper;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    List<Article> queryAll(@Param("author_id")String author_id,@Param("page")Integer page,@Param("rows")Integer rows);
    Integer getCount(String author_id);
    void update(Article article);
    void add(Article article);
    void updateVoice(Article article);
    void delete(String[] id);
}