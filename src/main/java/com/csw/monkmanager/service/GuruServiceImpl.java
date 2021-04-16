package com.csw.monkmanager.service;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.dao.GuruDao;
import com.csw.monkmanager.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> selectAll() {
        return guruDao.selectAll();
    }

    @Override
    @LogAnnotation(value = "添加上师信息")
    public Map<String,Object> add(Guru guru) {
        HashMap<String,Object> hashMap = new HashMap<>();
        String s = UUID.randomUUID().toString();
        guru.setId(s);
        guruDao.insert(guru);
        hashMap.put("guruId", s);
        hashMap.put("status", 200);

        return hashMap;
    }

    @Override
    @LogAnnotation(value = "修改上师信息")
    public Map<String,Object> update(Guru guru) {
        HashMap<String,Object> hashMap = new HashMap<>();
        guru.setPhoto(null);
        guruDao.updateByPrimaryKeySelective(guru);
        hashMap.put("guruId", guru.getId());
        hashMap.put("status", 200);
        hashMap.put("msg", "更新成功");
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "删除上师信息")
    public Map<String,Object> delete(String[] id) {
        HashMap<String,Object> hashMap = new HashMap<>();
        guruDao.deleteByIdList(Arrays.asList(id));
        hashMap.put("status", 200);
        hashMap.put("msg", "删除成功");
        return hashMap;
    }

    @Override
    public void updateByPrimaryKeySelective(Guru guru) {
        guruDao.updateByPrimaryKeySelective(guru);
    }
}
