package com.csw.monkmanager.dao;

import com.csw.monkmanager.entity.Chapter;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterDao extends Mapper<Chapter>, DeleteByIdListMapper<Chapter, String> {

    List<Chapter> selectByAlbumId(String id);
}
