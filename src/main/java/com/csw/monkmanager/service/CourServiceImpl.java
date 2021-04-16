package com.csw.monkmanager.service;

import com.csw.monkmanager.dao.CourseDao;
import com.csw.monkmanager.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> selectBuUid(String id) {
        return courseDao.selectBuUid(id);
    }
}
