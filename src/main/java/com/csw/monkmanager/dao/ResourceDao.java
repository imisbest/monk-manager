package com.csw.monkmanager.dao;

import com.csw.monkmanager.entity.Resource;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

@CacheNamespace
public interface ResourceDao extends Mapper<Resource>, DeleteByIdListMapper<Resource, String> {

}
