package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.service.AdminService;
import org.apache.ibatis.binding.MapperMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;


    @RequestMapping("/login")
    @ResponseBody
    public Map<String,String> login(String username, String password, String code, HttpSession session){
        HashMap<String,String> map = new HashMap<String,String>();
        Admin admin = adminService.findByName(username);
        if(admin == null){
            map.put("message","用户名不存在");
            return map;
        }
        String session_code = (String) session.getAttribute("SESSION_CODE");
        if(admin.getPassword().equals(password)){
            System.out.println(session_code);
            System.out.println(code);
            if(session_code.equals(code)){
                session.setAttribute("loginUser",admin);
                map.put("message","ok");
                return map;
            }else{
                map.put("message","验证码错误！");
                return map;
            }
        }else{
            map.put("message","密码错误！");
            return map;
        }
    }

    @RequestMapping("/vueLogin")
    @ResponseBody
    public Map<String,String> login(@RequestBody Map map){
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        HashMap<String,String> hashMap = new HashMap<>();

        Admin admin = adminService.findByName(username);
        if(admin != null){
            if(admin.getPassword().equals(password)){
                hashMap.put("message","ok");
            }else{
                hashMap.put("message","no");
            }
        }else{
            hashMap.put("message","no");
        }
        return hashMap;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/login/login.jsp";
    }
}