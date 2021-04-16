package com.csw.monkmanager.service;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.annotation.LogAnnotation2;
import com.csw.monkmanager.dao.UserDao;
import com.csw.monkmanager.entity.MapVO;
import com.csw.monkmanager.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> showAllUsers(Integer page, Integer rows) {
        HashMap<String,Object> hashMap = new HashMap<>();
        List<User> users = userDao.selectByRowBounds(new User(), new RowBounds((page - 1) * rows, rows));
        int records = userDao.selectCount(new User());
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        hashMap.put("rows", users);
        hashMap.put("page", page);
        hashMap.put("records", records);
        hashMap.put("total", total);
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "更新用户信息")
    @LogAnnotation2(value = "更新用户信息")
    public Map<String,Object> update(User user) {
        HashMap<String,Object> hashMap = new HashMap<>();
        //user.setUrl(null);
        userDao.updateByPrimaryKeySelective(user);
        hashMap.put("userId", user.getId());
        hashMap.put("status", 200);
        hashMap.put("msg", "更新成功");
        return hashMap;
    }

    @Override
    public Integer find1(String sex1, String s) {
        return userDao.find1(sex1, s);
    }

    @Override
    public Integer find2(String sex2, String s) {
        return userDao.find2(sex2, s);
    }

    @Override
    public List<MapVO> showAllMap(String sex1) {
        return userDao.showAllMap(sex1);
    }

    @Override
    @LogAnnotation2(value = "删除用户信息")
    @LogAnnotation(value = "删除用户信息")
    public Map<String,Object> delete(String[] id) {
        HashMap<String,Object> hashMap = new HashMap<>();
        userDao.deleteByIdList(Arrays.asList(id));
        hashMap.put("status", 200);
        hashMap.put("msg", "删除成功");
        return hashMap;
    }

    @Override
    public Map<String,Object> update2(User user) {
        HashMap<String,Object> hashMap = new HashMap<>();
        //user.setUrl(null);
        userDao.updateByPrimaryKeySelective(user);
        //User uu=userDao.s
        hashMap.put("userId", user.getId());
        hashMap.put("status", 200);
        hashMap.put("msg", "更新成功");
        return hashMap;
    }


}
