package com.csw.monkmanager.aspect;

import com.csw.monkmanager.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by HIAPAD on 2019/11/28.
 */
@Aspect
@Configuration
public class LogAspect {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Around("@annotation(com.csw.monkmanager.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint) {
        /*
            谁 时间 事件 成功与否
         */
        // 谁
        HttpSession session = request.getSession();
        session.setAttribute("admin", "admin");
        String admin = (String) session.getAttribute("admin");
        // 时间
        Date date = new Date();
        // 获取方法名
        String name = proceedingJoinPoint.getSignature().getName();
       //方法名】" + name);
        //类名】" + leiming);
        // 获取注解信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        String value = annotation.value();
       //value;;" + value);
        try {
            Object proceed = proceedingJoinPoint.proceed();
            String status = "success";
            System.out.println(admin + " " + date + " " + name + " " + status);
            stringRedisTemplate.opsForHash().put("rizhi", admin + " " + date + " ", value + " " + status);//hash类型操作
            return proceed;
        } catch (Throwable throwable) {
            String status = "error";
            System.out.println(admin + " " + date + " " + name + " " + status);
            stringRedisTemplate.opsForHash().put("rizhi", admin + " " + date + " ", value + " " + status);//hash类型操作
            throwable.printStackTrace();
            return null;
        }
    }
}
