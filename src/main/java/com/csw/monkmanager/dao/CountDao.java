package com.csw.monkmanager.dao;

import com.csw.monkmanager.entity.Counter;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.common.Mapper;

@CacheNamespace
public interface CountDao extends Mapper<Counter> {

}
