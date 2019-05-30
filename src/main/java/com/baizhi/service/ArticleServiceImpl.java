package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Map<String, Object> queryAll(String author_id, Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        Integer start = (page - 1) * rows;
        List<Article> articles = articleMapper.queryAll(author_id, start, rows);
        System.out.println(articles);

        Integer count = articleMapper.getCount(author_id);
        Integer total = count % rows == 0 ? count / rows : (count / rows) + 1;

        map.put("page", page);
        map.put("total", total);
        map.put("records", count);
        map.put("rows", articles);
        return map;
    }

    @Override
    public String add(Article article) {
        String id = UUID.randomUUID().toString();
        article.setId(id);
        article.setCreate_date(new Date());
        articleMapper.add(article);
        return id;
    }

    @Override
    public void updateVoice(String articleId, MultipartFile voice, HttpSession session) {
        try {
            if("".equals(voice.getOriginalFilename())){
                return;
            }
            //判断上传文件夹是否存在
            String realPath = session.getServletContext().getRealPath("/upload/music");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String originalFilename = voice.getOriginalFilename();
            String str = new Date().getTime() + "_" + originalFilename;
            voice.transferTo(new File(realPath, str));

            //获取时长
            AudioFile audioFile = AudioFileIO.read(new File(realPath, str));
            AudioHeader audioHeader = audioFile.getAudioHeader();
            int trackLength = audioHeader.getTrackLength();
            String seconds = trackLength % 60 + "秒";
            String minute = trackLength / 60 + "分";

            //获取大小
            String size = (voice.getSize() / 1024 / 1024)+"MB";


            Article article = new Article();
            article.setId(articleId).setSize(size).setDuration(minute+seconds).setVoice(str);
            articleMapper.updateVoice(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String[] id) {
        articleMapper.delete(id);
    }

    @Override
    public String update(Article article) {
        String id = article.getId();
        articleMapper.update(article);
        return id;
    }

    @Override
    public void downLoadAudio(String audioName, HttpServletResponse response, HttpSession session) {
        //1.获得音频的目录
        String realPath = session.getServletContext().getRealPath("/upload/music");
        //2.获取音频的文件
        File file = new File(realPath,audioName);
        //3.设置响应头(以附件的形式下载)
        ServletOutputStream outputStream = null;
        String str = audioName.split("-")[1];
        try{
            String encode = URLEncoder.encode(str, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + encode);
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file,outputStream);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}