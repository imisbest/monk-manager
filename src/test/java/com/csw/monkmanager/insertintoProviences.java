package com.csw.monkmanager;

import com.csw.monkmanager.dao.UserDao;
import com.csw.monkmanager.entity.User;
import com.csw.monkmanager.service.UserService;
import com.csw.monkmanager.util.DateRandomTest;
import com.csw.monkmanager.util.MD5Utils;
import com.csw.monkmanager.util.Number10;
import com.csw.monkmanager.util.Number6;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class insertintoProviences {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Test
    public void insertProviences() {
        String[] zhanshi = {"展示", "冻结"};
        String[] name = {"小张", "小红", "小李", "小明"};
        String[] sex = {"男", "女"};
        String[] location = {"北京", "天津", "上海", " 重庆", " 河北", "河南", "云南", " 辽宁", "湖南", "安徽", "山东", "新疆",
                "江苏", "浙江", "江西", "湖北", "广西", "甘肃", "山西", "陕西", "吉林", "福建", "贵州", "广东", "青海", "西藏",
                "四川", "宁夏", "海南", "台湾", "香港", "澳门", "内蒙古", "黑龙江"};
        for (int i = 0; i < 20; i++) {
            User uu = new User();
            uu.setId(UUID.randomUUID().toString());
            uu.setPhone(1 + Number10.getNum());
            uu.setPassword(Number6.getNum());
            uu.setSalt(MD5Utils.getSalt());
            uu.setStatus(zhanshi[new Random().nextInt(zhanshi.length)]);
            uu.setPhoto(null);
            uu.setName(name[new Random().nextInt(name.length)] + Number6.getNum());
            uu.setNick_name(null);
            uu.setSex(sex[new Random().nextInt(sex.length)]);
            uu.setSign(null);
            uu.setLocation(location[new Random().nextInt(location.length)]);
            uu.setRigest_date(DateRandomTest.date());
            uu.setLast_login(null);
           //现在正在插入】" + i + "【条数据");
            userDao.insert(uu);
        }

    }

    @Test
    public void deleteAll() {
        List<User> articles = userDao.selectAll();
        for (int i = 0; i < articles.size(); i++) {
            userDao.delete(articles.get(i));
        }
    }

}
