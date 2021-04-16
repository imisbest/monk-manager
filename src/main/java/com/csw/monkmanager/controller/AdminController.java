package com.csw.monkmanager.controller;

import com.csw.monkmanager.annotation.CacheAnnotation;
import com.csw.monkmanager.annotation.DeleteAnnotation;
import com.csw.monkmanager.dao.AdminDao;
import com.csw.monkmanager.dao.ResourceDao;
import com.csw.monkmanager.entity.Admin;
import com.csw.monkmanager.entity.Resource;
import com.csw.monkmanager.service.AdminService;
import com.csw.monkmanager.service.ResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceDao resourceDao;

    @RequestMapping("login")
    @ResponseBody
    public String backlogin(Admin admin, String captchaCode, HttpSession session) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        Subject subject = SecurityUtils.getSubject();
        String code = (String) session.getAttribute("securityCode");
        if (code.equalsIgnoreCase(captchaCode)) {
            try {
                subject.login(usernamePasswordToken);
                return null;
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "用户名或者密码错误";
            }
        } else {
            return "验证码错误";
        }
    }

    @RequestMapping("logout")
    public String logout() {
        // 获取主体信息
        Subject subject = SecurityUtils.getSubject();
        // 执行登出方法
        subject.logout();
        return "redirect:/jsp/back/login.jsp";
    }


    @RequestMapping("showAlladmins")
    @ResponseBody
    @CacheAnnotation()
    public Map<String,Object> showAlladmins(Integer page, Integer rows) {
        return adminService.showAllAdmins(page, rows);
    }

    @RequestMapping("edit")
    @ResponseBody
    @DeleteAnnotation()
    public String edit(String oper, String resource_id, String resource_name) {

        switch (oper) {
            case "add": {
                Resource resource = new Resource();
                resource.setId(UUID.randomUUID().toString()).setResource_name(resource_name);
                resourceDao.insertSelective(resource);
                break;
            }
            case "edit": {
                Resource resource = new Resource();
                resource.setId(resource_id).setResource_name(resource_name);
                resourceDao.updateByPrimaryKeySelective(resource);
                break;
            }
            case "del": {
                Resource resource = new Resource();
                resource.setId(resource_id).setResource_name(resource_name);
                resourceDao.deleteByPrimaryKey(resource);
                break;
            }
        }
        return null;
    }


}
