package com.csw.monkmanager.service;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.dao.ChapterDao;
import com.csw.monkmanager.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ChapterserviceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> showAllBanners(Integer page, Integer rows, String id) {
        HashMap<String,Object> hashMap = new HashMap<>();
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(id);
        List<Chapter> Chapters = chapterDao.selectByRowBounds(chapter, new RowBounds((page - 1) * rows, rows));
        int records = chapterDao.selectCount(chapter);
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        hashMap.put("rows", Chapters);
        hashMap.put("page", page);
        hashMap.put("records", records);
        hashMap.put("total", total);
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "添加章节信息")
    public Map<String,Object> add(Chapter chapter, String albumId) {
        HashMap<String,Object> hashMap = new HashMap<>();
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapter.setAlbum_id(albumId);
        chapterDao.insert(chapter);
        hashMap.put("chapterId", s);
        hashMap.put("status", 200);

        return hashMap;
    }

    @Override
    @LogAnnotation(value = "更新章节信息")
    public Map<String,Object> update(Chapter Chapter) {
        HashMap<String,Object> hashMap = new HashMap<>();
        Chapter.setUrl(null);
        chapterDao.updateByPrimaryKeySelective(Chapter);
        hashMap.put("chapterId", Chapter.getId());
        hashMap.put("status", 200);
        hashMap.put("msg", "更新成功");
        return hashMap;
    }

    @Override
    public void updateByPrimaryKeySelective(Chapter chapter) {
        chapterDao.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public List<Chapter> selectByAlbumId(String id) {
        return chapterDao.selectByAlbumId(id);
    }

    @Override
    @LogAnnotation(value = "删除章节信息")
    public Map<String,Object> delete(String[] id) {


        HashMap<String,Object> hashMap = new HashMap<>();
        chapterDao.deleteByIdList(Arrays.asList(id));
        hashMap.put("status", 200);
        hashMap.put("msg", "删除成功");
        return hashMap;
    }
}
