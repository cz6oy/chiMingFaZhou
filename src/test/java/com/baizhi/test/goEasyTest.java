package com.baizhi.test;

import com.baizhi.App;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class goEasyTest {

    @Test
    public void demo1(){
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-dc51cc58bdf943e3be267bb15de4d5a7");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }
}