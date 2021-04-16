package com.csw.monkmanager.controller;

import com.csw.monkmanager.util.SecurityCode;
import com.csw.monkmanager.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

@Controller
@RequestMapping("captcha")
public class CaptchaAction {
    @RequestMapping("captcha")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String securityCode = SecurityCode.getSecurityCode();
        HttpSession session = request.getSession();
        session.setAttribute("securityCode", securityCode);
        BufferedImage image = SecurityImage.createImage(securityCode);
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        return null;
    }
}