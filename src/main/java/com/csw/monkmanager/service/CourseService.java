package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> selectBuUid(String id);
}
