package com.baizhi;

import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class test01 {

//    @Autowired
//    private BannerService bannerService;
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void demo1(){
        List<Article> articles = articleMapper.queryAll("1", 0, 5);
        System.out.println(articles);
    }
}
