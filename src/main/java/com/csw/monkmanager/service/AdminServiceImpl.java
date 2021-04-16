package com.csw.monkmanager.service;

import com.csw.monkmanager.dao.AdminDao;
import com.csw.monkmanager.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String,Object> showAllAdmins(Integer page, Integer rows) {
        HashMap<String,Object> hashMap = new HashMap<>();
        //List<Admin> admins = adminDao.selectByRowBounds(new Admin(), new RowBounds((page - 1) * rows, rows));
        Integer start = (page - 1) * rows;
        List<Admin> admins = adminDao.findByPage(start, rows);
        int records = adminDao.selectCount(new Admin());
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        hashMap.put("rows", admins);
        hashMap.put("page", page);
        hashMap.put("records", records);
        hashMap.put("total", total);
        return hashMap;
    }

    @Override
    public Map<String,Object> add(Admin admin) {
        HashMap<String,Object> hashMap = new HashMap<>();
        String s = UUID.randomUUID().toString();
        admin.setId(s);
        adminDao.insert(admin);
        hashMap.put("adminId", s);
        hashMap.put("status", 200);
        return hashMap;
    }

    @Override
    public Map<String,Object> update(Admin admin) {
        HashMap<String,Object> hashMap = new HashMap<>();
        adminDao.updateByPrimaryKeySelective(admin);
        hashMap.put("adminId", admin.getId());
        hashMap.put("status", 200);
        hashMap.put("msg", "更新成功");
        return hashMap;
    }

    @Override
    public Map<String,Object> delete(String[] id) {


        HashMap<String,Object> hashMap = new HashMap<>();
        adminDao.deleteByIdList(Arrays.asList(id));
        hashMap.put("status", 200);
        hashMap.put("msg", "删除成功");
        return hashMap;
    }

    @Override
    public void updateByPrimaryKeySelective(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }
}
