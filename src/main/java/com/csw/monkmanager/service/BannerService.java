package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.Banner;

import java.util.Map;

public interface BannerService {
    Map<String,Object> showAllBanners(Integer page, Integer rows);

    Map<String,Object> add(Banner banner);

    Map<String,Object> update(Banner banner);

    Map<String,Object> delete(String[] id);

    void updateByPrimaryKeySelective(Banner banner);
}
