package com.csw.monkmanager.controller;

import com.csw.monkmanager.annotation.CacheAnnotation;
import com.csw.monkmanager.annotation.DeleteAnnotation;
import com.csw.monkmanager.dao.UserDao;
import com.csw.monkmanager.entity.MapVO;
import com.csw.monkmanager.entity.User;
import com.csw.monkmanager.service.UserService;
import com.csw.monkmanager.task.ControllerTask;
import com.csw.monkmanager.util.MD5Utils;
import com.csw.monkmanager.util.MessageSend;
import com.csw.monkmanager.util.Number6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ControllerTask controllerTask;
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

   //2. 发送验证码

    @RequestMapping("sendm")
    public Map<String,Object> sendmessage(String phone) {
        Map<String,Object> map = new HashMap<>();
        String aa = Number6.getNum();
        try {
            MessageSend.send(phone, aa);
            stringRedisTemplate.opsForValue().set(phone, aa, 5, TimeUnit.MINUTES);
            map.put("status", 200);
            map.put("message", "成功");
        } catch (Exception e) {
            map.put("status", 500);
            map.put("message", "失败");
            e.printStackTrace();
        }
        return map;
    }

   // 3. 注册接口

    @RequestMapping("yanzm")
    public Map<String,Object> yanzm(HttpSession session, String captchaCode) {
        Map<String,Object> map = new HashMap<>();
        String code = (String) session.getAttribute("securityCode");
        if (code.equalsIgnoreCase(captchaCode)) {
            map.put("status", 200);
            map.put("message", "成功");
        } else {
            map.put("status", 500);
            map.put("message", "失败");
        }
        return map;
    }

   // 4. 补充个人信息接口

    @RequestMapping("buchong")
    public Map<String,Object> buchong(User user, HttpSession session) {
        Map<String,Object> map = new HashMap<>();
//模拟session

        session.setAttribute("user", userDao.selectByPrimaryKey(new User().setId("72893621-7024-4b0d-b06e-cf7dff5afb6e")));
        try {
            User uu = (User) session.getAttribute("user");
            //注意id要从session中获取
            user.setId(uu.getId());
            //注意密码加盐
            String salt = MD5Utils.getSalt();
            uu.setSalt(salt);
            uu.setPassword(MD5Utils.getPassword(salt + user.getPassword()));
            userDao.updateByPrimaryKeySelective(user);
            map.put("status", 200);
            User user1 = userDao.selectByPrimaryKey(user);
            user.setRigest_date(null);
            user.setLast_login(null);
            map.put("user", user1);
        } catch (Exception e) {
            map.put("status", 500);
            map.put("message", "失败");
            e.printStackTrace();
        }
        return map;
    }

    // 15. 修改个人信息

    @RequestMapping("updateUserInformation")
    public Map<String,Object> updateUserInformation(User user) {
        Map<String,Object> map = new HashMap<>();
        userDao.updateByPrimaryKeySelective(user);
        User user1 = userDao.selectByPrimaryKey(user);
        map.put("status", 200);
        map.put("user", user1);
        return map;
    }

    //后台开始

    @CacheAnnotation()
    @RequestMapping("showAllusers")
    public Map<String,Object> showAllUsers(Integer page, Integer rows, HttpSession session) {
       //[进入查询所有]");
        Map<String,Object> map = userService.showAllUsers(page, rows);
        controllerTask.run(session);
        return map;
    }

    @RequestMapping("edit")
    @DeleteAnnotation()
    public Map<String,Object> edit(String oper, User user, String[] id) {
        Map<String,Object> hashMap = new HashMap<>();
        if (oper.equals("edit")) {
            hashMap = userService.update(user);
        } else if (oper.equals("del")) {
            hashMap = userService.delete(id);
        }
        return hashMap;
    }

    @RequestMapping("usershowAll")
    public Map<String, List<Integer>> usershowAll() {
        Map<String, List<Integer>> map = new HashMap<>();
        String sex1 = "男";
        String sex2 = "女";
        List<Integer> list1 = new ArrayList<>();
       //userService.find1(sex1, \"1\")" + userService.find1(sex1, "1"));
       //userService.find1(sex2, \"1\")" + userService.find1(sex2, "1"));
        list1.add(userService.find1(sex1, "1"));
        list1.add(userService.find1(sex1, "7"));
        list1.add(userService.find1(sex1, "30"));
        list1.add(userService.find1(sex1, "365"));
        map.put("man", list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(userService.find2(sex2, "1"));
        list2.add(userService.find2(sex2, "7"));
        list2.add(userService.find2(sex2, "30"));
        list2.add(userService.find2(sex2, "365"));
        map.put("women", list2);
       //map]" + map);
        return map;
    }

    @RequestMapping("showAllMap")
    public Map<String, List<MapVO>> showAllMap() {
        Map<String, List<MapVO>> map = new HashMap<>();
        String sex1 = "男";
        List<MapVO> mapVOS1 = userService.showAllMap(sex1);
        map.put("man", mapVOS1);
        String sex2 = "女";
        List<MapVO> mapVOS2 = userService.showAllMap(sex2);
        map.put("women", mapVOS2);
        return map;
    }

    //1. 登陆接口

    @RequestMapping("login")
    public Map<String,Object> login(User user) {
        Map<String,Object> map = new HashMap<>();
        User user2 = userDao.selectByAB(user);
        if (user2 != null) {
            map.put("status", 200);
            map.put("user", user2);
        } else {
            map.put("status", "500");
            map.put("mesage", "error");
        }
        return map;
    }

    @RequestMapping("update")
    public Map<String,Object> update(User user) {
        return userService.update2(user);
    }

}
