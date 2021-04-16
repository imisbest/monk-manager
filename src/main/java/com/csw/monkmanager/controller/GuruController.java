package com.csw.monkmanager.controller;

import com.csw.monkmanager.annotation.CacheAnnotation;
import com.csw.monkmanager.annotation.DeleteAnnotation;
import com.csw.monkmanager.dao.GuruDao;
import com.csw.monkmanager.dao.UserDao;
import com.csw.monkmanager.entity.Guru;
import com.csw.monkmanager.entity.User;
import com.csw.monkmanager.service.GuruService;
import com.csw.monkmanager.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.*;

@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private GuruDao guruDao;
    @Autowired
    private UserDao userDao;

    @CacheAnnotation()
    @RequestMapping("showAllGuru")
    public List<Guru> showAllGuru() {
        return guruService.selectAll();
    }

    @RequestMapping("edit")
    @DeleteAnnotation()
    public Map<String,Object> edit(String oper, Guru guru, String[] id) {
        Map<String,Object> hashMap = new HashMap<>();
        switch (oper) {
            case "add":
                hashMap = guruService.add(guru);
                break;
            case "edit":
                hashMap = guruService.update(guru);
                break;
            case "del":

                hashMap = guruService.delete(id);
                break;
        }
        return hashMap;
    }


    @RequestMapping("upload")
    public void upload(MultipartFile photo, String guruId, HttpSession session, HttpServletRequest request) throws UnknownHostException {

       //photo]" + photo);// 获取路径
       //guruId]" + guruId);
        String httpUrl = HttpUtil.getHttpUrl(photo, request, session, "/upload/GuruImg/");
        Guru guru = new Guru();
        guru.setId(guruId);
        guru.setPhoto(httpUrl);
       //httpUrl]" + httpUrl);
       //guru]" + guru);
        guruService.updateByPrimaryKeySelective(guru);
        //guruDao.updateByPrimaryKeySelective(guru);
        System.out.println(photo);
        System.out.println(guruId);
    }

    //16. 金刚道友

    @RequestMapping("jgdy")
    public Map<String,Object> jgdy(String uid) {
        Map<String,Object> map = new HashMap<>();
        Set<String> members = stringRedisTemplate.opsForSet().members(uid);
/*//        String[] s1 = new String[set.size()];
//        String[] s2 = set.toArray(s1);
//
//        for (int i = 0; i < s2.length; i++) {
//            String s3 = s2[i];
//           //222---"+s3);
//        }*/

        assert members != null;
        String[] strings = (String[]) members.toArray();
        int num = new Random().nextInt(strings.length);
        String guid1;
        String guid2;
        if (num < strings.length - 1) {
            guid1 = strings[num];
            guid2 = strings[num + 1];
        } else {
            guid1 = strings[num];
            guid2 = strings[num + 1 - strings.length];
        }
        Set<String> members1 = stringRedisTemplate.opsForSet().members(guid1);
        Set<String> members2 = stringRedisTemplate.opsForSet().members(guid2);
        assert members1 != null;
        Set<String> set = new HashSet<>(members1);
        assert members2 != null;
        set.retainAll(members2);
/*//        result.clear();
//        result.addAll(set1);
//        result.retainAll(set2);
//       //交集：" + result);
//
//        result.clear();
//        result.addAll(set1);
//        result.removeAll(set2);
//       //差集：" + result);
//
//        result.clear();
//        result.addAll(set1);
//        result.addAll(set2);
//       //并集：" + result);*/
        List<User> userList = new ArrayList<>();
        for (String ss : set) {
            userList.add(userDao.selectByPrimaryKey(new User().setId(ss)));
        }
        map.put("status", 200);
        map.put("list", userList);
        return map;
    }

   //17. 展示上师列表

    @RequestMapping("showGuru")
    public Map<String,Object> showGuru(String uid) {
        Map<String,Object> map = new HashMap<>();
        Set<String> members = stringRedisTemplate.opsForSet().members(uid);
        List<Guru> guruList = new ArrayList<>();
        assert members != null;
        for (String member : members) {
            guruList.add(guruDao.selectByPrimaryKey(new Guru().setId(member)));
        }
        map.put("status", 200);
        map.put("list", guruList);
        return map;
    }

    //18. 添加关注上师

    @RequestMapping("viewGuru")
    public Map<String,Object> viewGuru(String uid, String id) {
        Map<String,Object> map = new HashMap<>();
        SetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForSet();
        stringStringSetOperations.add(uid, id);
        stringStringSetOperations.add(id, uid);
        //aa
        Set<String> members = stringStringSetOperations.members(uid);
        List<Guru> guruList = new ArrayList<>();
        assert members != null;
        for (String member : members) {
            guruList.add(guruDao.selectByPrimaryKey(new Guru().setId(member)));
        }
        map.put("status", 200);
        map.put("list", guruList);
        return map;
    }

}
