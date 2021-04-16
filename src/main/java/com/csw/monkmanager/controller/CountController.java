package com.csw.monkmanager.controller;

import com.csw.monkmanager.dao.CountDao;
import com.csw.monkmanager.entity.Counter;
import com.csw.monkmanager.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("count")
@RestController
public class CountController {
    @Autowired
    private CountService countService;
    @Autowired
    private CountDao countDao;

   //11. 展示计数器

    @RequestMapping("showCount")
    public Map<String,Object> showCount() {
        Map<String,Object> map = new HashMap<>();
        map.put("status", 200);
        List<Counter> counters = countDao.selectAll();
        map.put("counters", counters);
        return map;
    }

    //12. 添加计数器

    @RequestMapping("addCount")
    public Map<String,Object> addCount(String title, String uid) {
        Map<String,Object> map;
        map = countService.addCount(title, uid);
        return map;
    }

    //13. 删除计数器

    @RequestMapping("deleteCount")
    public Map<String,Object> deleteCount(String uid, String id) {
        Map<String,Object> map;
        map = countService.deleteCount(uid, id);
        return map;
    }

    //14. 表更计数器

    @RequestMapping("updateCount")
    public Map<String,Object> updateCount(String uid, String id, String count) {
        Map<String,Object> map;
        map = countService.updateCount(uid, id, count);
        return map;
    }


}
