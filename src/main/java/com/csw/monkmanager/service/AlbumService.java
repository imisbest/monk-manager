package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.Album;

import java.util.Map;

public interface AlbumService {
    Map<String,Object> showAllAlbums(Integer page, Integer rows);

    Map<String,Object> add(Album album);

    Map<String,Object> update(Album album);

    Map<String,Object> delete(String[] id);

    void updateByPrimaryKeySelective(Album album);
}
