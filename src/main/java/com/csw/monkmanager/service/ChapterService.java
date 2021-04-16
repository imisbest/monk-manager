package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    Map<String,Object> showAllBanners(Integer page, Integer rows, String id);


    Map<String,Object> delete(String[] id);

    Map<String,Object> add(Chapter chapter, String albumId);

    Map<String,Object> update(Chapter chapter);

    void updateByPrimaryKeySelective(Chapter chapter);


    List<Chapter> selectByAlbumId(String id);
}
