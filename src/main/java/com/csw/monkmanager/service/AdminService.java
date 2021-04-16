package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.Admin;

import java.util.Map;

public interface AdminService {

    Map<String,Object> showAllAdmins(Integer page, Integer rows);

    Map<String,Object> add(Admin admin);

    Map<String,Object> update(Admin admin);

    Map<String,Object> delete(String[] id);

    void updateByPrimaryKeySelective(Admin admin);
}
