package com.baizhi.controller;

import com.baizhi.entity.Wztext;
import com.baizhi.service.WztextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/wztext")
public class WztextController {

    @Autowired
    private WztextService wztextService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = wztextService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("/addWztext")
    @ResponseBody
    public String addWztext(Wztext wztext){
        wztextService.addWztext(wztext);
        return "ok";
    }

    @RequestMapping("/editWztext")
    @ResponseBody
    public String editWztext(String id,String title,String author,String status,String content){
        if("".equals(content) || content.length()<0){
            content = null;
        }
        Wztext wztext = new Wztext();
        wztext.setId(id).setTitle(title).setAuthor(author).setStatus(status).setContent(content);
        wztextService.update(wztext);
        return "ok";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String delete(String[] id,String oper){
        if("del".equals(oper)){
            wztextService.delete(id);
        }
        return "ok";
    }
}
