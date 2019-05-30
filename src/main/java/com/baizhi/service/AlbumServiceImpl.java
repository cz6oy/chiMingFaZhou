package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public Map<String,Object> queryAll(Integer page,Integer rows) {
        HashMap<String,Object> map = new HashMap<>();

        Integer start = (page-1)*rows;
        List<Album> albums = albumMapper.queryAll(start,rows);
        Integer count = albumMapper.getCount();
        Integer total = count%rows==0?count/rows:count/rows+1;

        map.put("total",total);
        map.put("rows",albums);
        map.put("page",page);
        map.put("records",count);
        return map;
    }

    @Override
    public String add(String title, Integer score, String author, String broadcast, Integer count, String brief, Date create_date, String cover_pic) {
        Album album = new Album();
        String id = UUID.randomUUID().toString();
        album.setId(id);
        album.setTitle(title);
        album.setScore(score);
        album.setAuthor(author);
        album.setBroadcast(broadcast);
        album.setCount(count);
        album.setBrief(brief);
        album.setCreate_date(new Date());
        album.setCover_pic(cover_pic);
        albumMapper.add(album);
        return id;
    }

    @Override
    public void updateCoverPic(String cover_pic,String id) {
        albumMapper.updateCoverPic(cover_pic,id);
    }

    @Override
    public String update(String id, String title, Integer score, String author, String broadcast, Integer count, String brief, Date create_date, String cover_pic) {
        Album album = new Album();
        album.setId(id);
        album.setTitle(title);
        album.setScore(score);
        album.setAuthor(author);
        album.setBroadcast(broadcast);
        album.setCount(count);
        album.setBrief(brief);
        album.setCreate_date(new Date());
        album.setCover_pic(cover_pic);
        albumMapper.update(album);
        return id;
    }

    @Override
    public void delete(String[] id) {
        albumMapper.delete(id);
    }
}