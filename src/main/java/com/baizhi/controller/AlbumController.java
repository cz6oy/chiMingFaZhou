package com.baizhi.controller;

import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String,Object> map = albumService.queryAll(page,rows);
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String,String> edit(String[] id, String title, Integer score, String author, String broadcast, Integer count, String brief, Date create_date, String cover_pic, String oper){
        HashMap<String, String> map = new HashMap<>();
        if("add".equals(oper)){
            String addId = albumService.add(title, score, author, broadcast, count, brief, create_date, cover_pic);
            map.put("key",addId);
            map.put("msg","添加成功！");
        }else if("edit".equals(oper)){
            String updateId = albumService.update(id[0], title, score, author, broadcast, count, brief, create_date, cover_pic);
            map.put("key",updateId);
            map.put("msg","修改成功！");
        }else if("del".equals(oper)){
            albumService.delete(id);
            map.put("msg","删除成功！");
        }
        return map;
    }

    @RequestMapping("/upload")
    public String upload(String albumId,HttpSession session, MultipartFile cover_pic){
        if (!cover_pic.getOriginalFilename().equals("")) {
            //判断上传文件夹是否存在
            String realPath = session.getServletContext().getRealPath("/upload/img");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String originalFilename = cover_pic.getOriginalFilename();
            String str = new Date().getTime() + "_" + originalFilename;
            System.out.println("-----------"+str);
            albumService.updateCoverPic(str,albumId);
            try {
                cover_pic.transferTo(new File(realPath, str));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
