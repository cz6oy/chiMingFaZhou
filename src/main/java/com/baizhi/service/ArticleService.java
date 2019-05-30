package com.baizhi.service;

import com.baizhi.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ArticleService {
    Map<String,Object> queryAll(String author_id, Integer page, Integer rows);
    String add(Article article);
    void updateVoice(String articleId, MultipartFile voice,HttpSession session);
    void delete(String[] id);
    String update(Article article);
    void downLoadAudio(String audioName, HttpServletResponse response, HttpSession session);
}
