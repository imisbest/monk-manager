package com.csw.monkmanager.aspect;

import com.csw.monkmanager.service.UserService;
import com.csw.monkmanager.util.SerializeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Objects;

@Aspect
@Configuration
public class CacheRedisAop {
    private static final HashMap<String, Class> map = new HashMap<>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    @Around("@annotation(com.csw.monkmanager.annotation.CacheAnnotation)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();
       //方法名】" + name);
        String leiming = pjp.getTarget().getClass().getName();
       //类名】" + leiming);
        // 参数值
        Object[] args = pjp.getArgs();

        Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            if (!args[k].getClass().isPrimitive()) {
                // 获取的是封装类型而不是基础类型
                String result = args[k].getClass().getName();
                Class s = map.get(result);
                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        //arg]" + arg);
        //aClass]" + aClass);
        //classes]" + classes);


        HashOperations<String, Object, Object> sooh = stringRedisTemplate.opsForHash();
        StringBuilder qianzhui = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            qianzhui.append(classes[i]).append(args[i]);
        }
        String obj = (String) sooh.get(leiming, qianzhui + name);
        //存在直接返回，不再接下去执行查询数据库操作
        if (obj != null) {
           //【返回redis存储】");
            return SerializeUtils.serializeToObject(obj);
        }
        //不存在执行数据库操作
        Object proceed = pjp.proceed();
        //将查询的对象存入redis缓存
        sooh.put(leiming, qianzhui + name, Objects.requireNonNull(Objects.requireNonNull(SerializeUtils.serialize(proceed))));
        return proceed;
    }

    @Around("@annotation(com.csw.monkmanager.annotation.DeleteAnnotation)")
    public Object delete(ProceedingJoinPoint pjp) throws Throwable {
        String leiming = pjp.getTarget().getClass().getName();
        Object[] args = pjp.getArgs();
        Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            if (!args[k].getClass().isPrimitive()) {
                // 获取的是封装类型而不是基础类型
                String result = args[k].getClass().getName();
                Class s = map.get(result);
                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        StringBuilder qianzhui = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            qianzhui.append(classes[i]).append(args[i]);
        }
        stringRedisTemplate.delete(leiming);

//        //不存在执行数据库操作
        //        //将查询的对象存入redis缓存
       //【清空redis】");
        return pjp.proceed();
    }
}
