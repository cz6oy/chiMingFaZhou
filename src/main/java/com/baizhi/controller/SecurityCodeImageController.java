package com.baizhi.controller;

import com.baizhi.utils.ValidateImageCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RequestMapping("/code")
@Controller
public class SecurityCodeImageController {

    @RequestMapping("getCode")
    public void getCode(HttpSession session,HttpServletResponse response) {
        String code = ValidateImageCodeUtils.getSecurityCode();
        BufferedImage image = ValidateImageCodeUtils.createImage(code);
        session.setAttribute("SESSION_CODE",code);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image,"png",out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}