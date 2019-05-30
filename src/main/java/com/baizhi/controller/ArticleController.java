package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(String albumId, Integer page, Integer rows) {
        Map<String, Object> map = articleService.queryAll(albumId, page, rows);
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, Object> edit(String oper, String[] id, String albumId, Article article) {
        HashMap<String, Object> map = new HashMap<>();
        article.setAuthor_id(albumId);
        if ("add".equals(oper)) {
            String addId = articleService.add(article);
            map.put("key", addId);
        } else if ("edit".equals(oper)) {
            String updateId = articleService.update(article);
            map.put("key", updateId);
        } else if ("del".equals(oper)) {
            articleService.delete(id);
        }
        return map;
    }

    @RequestMapping("/upload")//修改问题
    public void upload(String articleId, MultipartFile voice, HttpSession session) {
            articleService.updateVoice(articleId, voice, session);
    }

    @RequestMapping("/downLoadAudio")
    public void downLoadAudio(String audioName, HttpServletResponse response, HttpSession session) {
        articleService.downLoadAudio(audioName,response,session);
    }
}