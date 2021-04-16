package com.csw.monkmanager.dao;

import com.csw.monkmanager.entity.Course;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@CacheNamespace
public interface CourseDao extends Mapper<Course> {

    List<Course> selectBuUid(String id);
}
