package com.csw.monkmanager.config;

import com.csw.monkmanager.realm.MyRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroFilter {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("/boot/**", "anon");
        linkedHashMap.put("/echarts/**", "anon");
        linkedHashMap.put("/img/**", "anon");
        linkedHashMap.put("/jqgrid/**", "anon");
        linkedHashMap.put("/kindeditor/**", "anon");
        linkedHashMap.put("/upload/**", "anon");
        linkedHashMap.put("/captcha/**", "anon");
        //登录方法放行
        linkedHashMap.put("/admin/login", "anon");
        linkedHashMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);

        shiroFilterFactoryBean.setLoginUrl("/jsp/back/login.jsp");
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        CacheManager cacheManager = new EhCacheManager();
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }
}
