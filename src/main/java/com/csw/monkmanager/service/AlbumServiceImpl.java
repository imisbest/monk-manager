package com.csw.monkmanager.service;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.dao.AlbumDao;
import com.csw.monkmanager.entity.Album;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> showAllAlbums(Integer page, Integer rows) {
        HashMap<String,Object> hashMap = new HashMap<>();
        List<Album> albums = albumDao.selectByRowBounds(new Album(), new RowBounds((page - 1) * rows, rows));
        //albums.forEach(al ->//service albums;;" + al));
        int records = albumDao.selectCount(new Album());
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        hashMap.put("rows", albums);
        hashMap.put("page", page);
        hashMap.put("records", records);
        hashMap.put("total", total);
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "添加专辑信息")
    public Map<String,Object> add(Album album) {
        HashMap<String,Object> hashMap = new HashMap<>();
        String s = UUID.randomUUID().toString();
        album.setId(s);
        albumDao.insert(album);
        hashMap.put("albumId", s);
        hashMap.put("status", 200);

        return hashMap;
    }

    @Override
    @LogAnnotation(value = "修改专辑信息")
    public Map<String,Object> update(Album album) {
        HashMap<String,Object> hashMap = new HashMap<>();
        album.setCover(null);
        albumDao.updateByPrimaryKeySelective(album);
        hashMap.put("albumId", album.getId());
        hashMap.put("status", 200);
        hashMap.put("msg", "更新成功");
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "删除专辑信息")
    public Map<String,Object> delete(String[] id) {
        HashMap<String,Object> hashMap = new HashMap<>();
        albumDao.deleteByIdList(Arrays.asList(id));
        hashMap.put("status", 200);
        hashMap.put("msg", "删除成功");
        return hashMap;
    }

    @Override
    public void updateByPrimaryKeySelective(Album album) {
        albumDao.updateByPrimaryKeySelective(album);
    }
}
