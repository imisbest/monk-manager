package com.csw.monkmanager.controller;

import com.csw.monkmanager.dao.AlbumDao;
import com.csw.monkmanager.dao.ArticleDao;
import com.csw.monkmanager.dao.BannerDao;
import com.csw.monkmanager.dao.ChapterDao;
import com.csw.monkmanager.entity.Banner;
import com.csw.monkmanager.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping("frontm")
@RestController
public class FromManController {

    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private ChapterService chapterService;

    //5. 一级页面展示接口

    @RequestMapping("firsts")
    public Map<String,Object> firsts() {
        Map<String,Object> map = new HashMap<>();
        List<Banner> banners = bannerDao.selectAll();
        int aa = new Random().nextInt(banners.size());
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(banners.get(aa));//
        for (int i = 1; i <= 4; i++) {
            if (aa + 1 <= banners.size()) {
                aa += 1;
            } else {
                aa = aa + 1 - banners.size();
            }
        }
        bannerList.add(banners.get(aa));
        map.put("status", 200);
        map.put("banner", bannerList);

        return map;

    }


}
