package com.csw.monkmanager.service;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.dao.BannerDao;
import com.csw.monkmanager.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> showAllBanners(Integer page, Integer rows) {
        HashMap<String,Object> hashMap = new HashMap<>();
        List<Banner> banners = bannerDao.selectByRowBounds(new Banner(), new RowBounds((page - 1) * rows, rows));
        int records = bannerDao.selectCount(new Banner());
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        hashMap.put("rows", banners);
        hashMap.put("page", page);
        hashMap.put("records", records);
        hashMap.put("total", total);
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "添加轮播图信息")
    public Map<String,Object> add(Banner banner) {
        HashMap<String,Object> hashMap = new HashMap<>();
        String s = UUID.randomUUID().toString();
        banner.setId(s);
        bannerDao.insert(banner);
        hashMap.put("bannerId", s);
        hashMap.put("status", 200);

        return hashMap;
    }

    @Override
    @LogAnnotation(value = "更新轮播图信息")
    public Map<String,Object> update(Banner banner) {
        HashMap<String,Object> hashMap = new HashMap<>();
        banner.setUrl(null);
        bannerDao.updateByPrimaryKeySelective(banner);
        hashMap.put("bannerId", banner.getId());
        hashMap.put("status", 200);
        hashMap.put("msg", "更新成功");
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "删除轮播图信息")
    public Map<String,Object> delete(String[] id) {


        HashMap<String,Object> hashMap = new HashMap<>();
        bannerDao.deleteByIdList(Arrays.asList(id));
        hashMap.put("status", 200);
        hashMap.put("msg", "删除成功");
        return hashMap;
    }

    @Override
    public void updateByPrimaryKeySelective(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }
}
