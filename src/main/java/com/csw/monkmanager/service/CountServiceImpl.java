package com.csw.monkmanager.service;

import com.csw.monkmanager.dao.CountDao;
import com.csw.monkmanager.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CountServiceImpl implements CountService {
    @Autowired
    private CountDao countDao;

    @Override
    public Map<String,Object> addCount(String title, String uid) {
        Map<String,Object> map = new HashMap<>();
        Counter counter = new Counter();
        counter.setTitle(title);
        counter.setUser_id(uid);
        counter.setId(UUID.randomUUID().toString());
        countDao.insert(counter);

        List<Counter> counterList = countDao.selectAll();
        map.put("status", 200);
        map.put("counters", counterList);
        return map;

    }

    @Override
    public Map<String,Object> deleteCount(String uid, String id) {
        Map<String,Object> map = new HashMap<>();
        Counter counter = new Counter();
        counter.setId(id);
        counter.setUser_id(uid);
        countDao.deleteByPrimaryKey(counter);

        List<Counter> counterList = countDao.selectAll();
        map.put("status", 200);
        map.put("counters", counterList);
        return map;
    }

    @Override
    public Map<String,Object> updateCount(String uid, String id, String count) {
        Map<String,Object> map = new HashMap<>();
        Counter counter = new Counter();
        counter.setId(id);
        counter.setUser_id(uid);
        counter.setCount(count);
        countDao.updateByPrimaryKeySelective(counter);

        List<Counter> counterList = countDao.selectAll();
        map.put("status", 200);
        map.put("counters", counterList);
        return map;
    }
}
