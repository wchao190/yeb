package com.xxx.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xxx.server.config.security.CaptchaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class CaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, mustrevalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        //------------------ 生成验证码部分----------------
        //验证码文本内容
        String text = defaultKaptcha.createText();
        //将验证码放入session中
        request.getSession().setAttribute("captcha",text);
        //根据文本内容生成图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outstream = null;
        try {
            outstream = response.getOutputStream();
            //输出图片, 格式为 jpg
            ImageIO.write(image,"jpg",outstream);
            outstream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
