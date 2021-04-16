package com.csw.monkmanager.aspect;

import com.csw.monkmanager.entity.MapVO;
import com.csw.monkmanager.service.UserService;
import com.google.gson.Gson;
import io.goeasy.GoEasy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Aspect
@Configuration
public class LogAspect2 {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    @Around("@annotation(com.csw.monkmanager.annotation.LogAnnotation2)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint) {
        /*
            谁 时间 事件 成功与否
         */
        // 谁
        HttpSession session = request.getSession();
        session.setAttribute("admin", "admin");
        // 时间
        // 获取方法名
        // 获取注解信息
        //value;;" + value);
        try {
            Object proceed = proceedingJoinPoint.proceed();
            //
            Map<String, List<MapVO>> map = new HashMap<>();
            String sex1 = "男";
            List<MapVO> mapVOS1 = userService.showAllMap(sex1);
            map.put("man", mapVOS1);
            String sex2 = "女";
            List<MapVO> mapVOS2 = userService.showAllMap(sex2);
            map.put("women", mapVOS2);
           //aspect]" + map);
            Gson gson = new Gson();
            String sss1 = gson.toJson(map);
            GoEasy goEasy1 = new GoEasy("http://rest-singapore.goeasy.io", "BC-0197363289a243e7a9b97f3e4a3e0f11");
            goEasy1.publish("cmfz", sss1);//content : json字符串
           //数据返回");
            /*
             * echarts
             */
            Map<String, List<Integer>> map2 = new HashMap<>();
            List<Integer> list3 = new ArrayList<>();
           //userService.find1(sex1, \"1\")" + userService.find1(sex1, "1"));
           //userService.find1(sex2, \"1\")" + userService.find1(sex2, "1"));
            list3.add(userService.find1(sex1, "1"));
            list3.add(userService.find1(sex1, "7"));
            list3.add(userService.find1(sex1, "30"));
            list3.add(userService.find1(sex1, "365"));
            map2.put("man", list3);
            List<Integer> list2 = new ArrayList<>();
            list2.add(userService.find2(sex2, "1"));
            list2.add(userService.find2(sex2, "7"));
            list2.add(userService.find2(sex2, "30"));
            list2.add(userService.find2(sex2, "365"));
            map2.put("women", list2);
           //aspect]" + map2);
            // Gson gson=new Gson();
            String sss2 = gson.toJson(map2);
            GoEasy goEasy2 = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-8c7f344f2ffc4974a88531acc725c2f9");
            goEasy2.publish("csw", sss2);//content : json字符串
           //数据返回");
            return proceed;
        } catch (Throwable throwable) {

            throwable.printStackTrace();
            return null;
        }
    }
}
