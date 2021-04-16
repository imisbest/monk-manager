package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.MapVO;
import com.csw.monkmanager.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> showAllUsers(Integer page, Integer rows);

    Map<String,Object> update(User user);

    Integer find1(String sex1, String s);

    Integer find2(String sex2, String s);

    List<MapVO> showAllMap(String sex1);

    Map<String,Object> delete(String[] id);

    Map<String,Object> update2(User user);
}
