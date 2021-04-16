package com.csw.monkmanager.controller;

import com.csw.monkmanager.dao.CourseDao;
import com.csw.monkmanager.entity.Course;
import com.csw.monkmanager.entity.User;
import com.csw.monkmanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("course")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseDao courseDao;

    //8. 展示功课

    @RequestMapping("showCourse")
    public Map<String,Object> showCourse(User user) {
        Map<String,Object> map = new HashMap<>();
        List<Course> courses = courseService.selectBuUid(user.getId());
        map.put("status", 200);
        map.put("option", courses);
        return map;
    }

    //9. 添加功课

    @RequestMapping("addCourse")
    public Map<String,Object> addCourse(String uid, Course course) {
        course.setUser_id(uid);
        Map<String,Object> map = new HashMap<>();
        map.put("status", 200);
        List<Course> courses = courseDao.selectAll();
        map.put("option", courses);
        return map;
    }

    //10. 删除功课

    @RequestMapping("deleteCourse")
    public Map<String,Object> deleteCourse(Course course) {
        courseDao.deleteByPrimaryKey(course);
        Map<String,Object> map = new HashMap<>();
        map.put("status", 200);
        List<Course> courses = courseDao.selectAll();
        map.put("option", courses);
        return map;
    }


}
