package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = userService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("/edit")
    public Map<String,String> edit(String oper, User user,String[] id){
        System.out.println(user);
        HashMap<String,String> map = new HashMap<>();
        if("add".equals(oper)){

        }else if("add".equals(oper)){

        }else if("del".equals(oper)){
            userService.delete(id);
            map.put("key","ok");
        }
        return map;
    }

    @RequestMapping("/updateStatus")
    public Map<String,String> updateStatus(String id){
        HashMap<String,String> map = new HashMap<>();
        User user = userService.findById(id);
        User u = new User();
        u.setId(id);
        if(user.getStatus().equals("正常")){
          u.setStatus("冻结");
        }else{
            u.setStatus("正常");
        }
        userService.update(u);
        map.put("message","ok");
        return map;
    }

    @RequestMapping("/outExcel")
    public void easyPoiOut(HttpServletRequest request, HttpServletResponse response){
        String realPath = request.getSession().getServletContext().getRealPath("/upload/img/");

        List<User> userList = userService.queryAllUser();
        for(User user : userList){
            user.setHead_pic(realPath+user.getHead_pic());
        }

        for(User user : userList){
            System.out.println("======="+user);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("150班学生","学生信息表"), User.class, userList);
        String encode = null;
        try {
            encode = URLEncoder.encode("150.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/vueUserQueryAll")
    @ResponseBody
    public List<User> queryAll(){
        List<User> userList = userService.queryAllUser();
        return userList;
    }
}
