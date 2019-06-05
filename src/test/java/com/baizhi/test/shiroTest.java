package com.baizhi.test;

import com.baizhi.entity.RoleDto;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class shiroTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void demo1(){
        List<String> list = adminMapper.findByPermission("1");
        for(String key:list){
            System.out.println(key);
        }
    }
}
