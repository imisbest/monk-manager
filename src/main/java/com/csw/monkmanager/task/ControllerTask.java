package com.csw.monkmanager.task;

import com.alibaba.excel.EasyExcel;
import com.csw.monkmanager.dao.UserDao;
import com.csw.monkmanager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ControllerTask {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private UserDao userDao;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public void run(HttpSession session) {
        Runnable runnable = () -> {
            List<User> users2 = new ArrayList<>();
            //书写业务代码
            String fileName = "C:\\Users\\Administrator\\Desktop\\新建文件夹1\\" + new Date().getTime() + "DemoData.xlsx";
            List<User> users = userDao.selectAll();
            for (User user : users) {
                String realPath = session.getServletContext().getRealPath("/upload/img");
               //realpath;;" + realPath);
                String url = user.getPhoto();
                System.out.println(url.split("/").length);
                String relpath5 = realPath + "\\" + url.split("/")[url.split("/").length - 1];
               //本机路径" + relpath5);
                user.setPhoto(relpath5);
                users2.add(user);
            }
            EasyExcel.write(fileName, User.class).sheet().doWrite(users2);
            System.out.println(new Date());
        };
        // 0 0 0 0 0 7
        //0/5 * * * * *
        threadPoolTaskScheduler.schedule(runnable, new CronTrigger("0 0 0 ? * 7"));
    }

}
