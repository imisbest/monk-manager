package com.csw.monkmanager.controller;

import com.csw.monkmanager.annotation.CacheAnnotation;
import com.csw.monkmanager.annotation.DeleteAnnotation;
import com.csw.monkmanager.entity.Banner;
import com.csw.monkmanager.service.BannerService;
import com.csw.monkmanager.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HIAPAD on 2019/11/27.
 */
@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("showAllBanners")
    @CacheAnnotation()
    public Map<String,Object> showAllBanners(Integer page, Integer rows) {
        return bannerService.showAllBanners(page, rows);
    }


    @RequestMapping("edit")
    @DeleteAnnotation()
    public Map<String,Object> edit(String oper, Banner banner, String[] id) {
        Map<String,Object> hashMap = new HashMap<>();
        switch (oper) {
            case "add":
                hashMap = bannerService.add(banner);
                break;
            case "edit":
                hashMap = bannerService.update(banner);
                break;
            case "del":

                hashMap = bannerService.delete(id);
                break;
        }
        return hashMap;
    }


    @RequestMapping("upload")
    public void upload(MultipartFile url, String bannerId, HttpSession session, HttpServletRequest request) throws UnknownHostException {

       //url]" + url);// 获取路径
       //bannerId]" + bannerId);
        String httpUrl = HttpUtil.getHttpUrl(url, request, session, "/upload/img/");
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setUrl(httpUrl);
        bannerService.updateByPrimaryKeySelective(banner);
        //bannerDao.updateByPrimaryKeySelective(banner);
        System.out.println(url);
        System.out.println(bannerId);
    }
}
