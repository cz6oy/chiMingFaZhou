package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map<String, String> edit(String oper, String[] id, String title, String img_pic, String status, String description) {
        HashMap<String, String> map = new HashMap<>();
        if (oper.equals("add")) {
            String strid = bannerService.add(title, img_pic, status, description);
            map.put("key", strid);
            map.put("msg","添加成功!");
            return map;
        } else if (oper.equals("del")) {
            try{
                bannerService.delete(id);
                map.put("msg","删除成功!");
            }catch(Exception e){
                map.put("msg","删除失败!");
                e.printStackTrace();
            }
            return map;
        } else if (oper.equals("edit")) {
            String strid = bannerService.update(id[0], title, status, new Date(), description);
            map.put("key", strid);
            map.put("msg","修改成功!");
            return map;
        }
        return null;
    }

    @RequestMapping("/upload")
    public String upload(String bannerId, MultipartFile img_pic, HttpSession session) {
        if (!img_pic.getOriginalFilename().equals("")) {
            //判断上传文件夹是否存在
            String realPath = session.getServletContext().getRealPath("/upload/img");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String originalFilename = img_pic.getOriginalFilename();
            String str = new Date().getTime() + "_" + originalFilename;
            bannerService.updateHeadPic(str, bannerId);
            try {
                img_pic.transferTo(new File(realPath, str));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}