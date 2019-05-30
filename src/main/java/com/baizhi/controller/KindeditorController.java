package com.baizhi.controller;

import com.baizhi.service.KindeditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Map;

@Controller
@RequestMapping("/kindeditor")
public class KindeditorController {

    @Autowired
    private KindeditorService kindeditorService;

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Map<String,Object> kindeditor(MultipartFile img,HttpServletRequest request){
        Map<String, Object> map = kindeditorService.uploadImage(request, img);
        return map;
    }

    @RequestMapping("/getAllImage")
    @ResponseBody
    public Map<String,Object> getAllImage(HttpServletRequest request){
        Map<String, Object> map = null;
        try {
            map = kindeditorService.getAllImager(request);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }
}