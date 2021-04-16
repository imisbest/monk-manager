package com.csw.monkmanager.task;//package com.baizhi.task;

import com.csw.monkmanager.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by HIAPAD on 2019/12/3.
 * fixedDelay: 当定时任务执行完毕时开始计时
 * fixedRate: 当定时任务开启时计时
 */
@Component
@Async
public class TestSpringTask {
    @Autowired
    private UserDao userDao;

    @Scheduled(cron = "0 0/3 * * * ? ")
    public void task01() {
//        List<User> users2 = new ArrayList<>();
//        //书写业务代码
//        String fileName = "C:\\Users\\Administrator\\Desktop\\新建文件夹1\\" + new Date().getTime() + "DemoData.xlsx";
//        List<User> users = userDao.selectAll();
//        for (User user : users) {
//            String realPath = session.getServletContext().getRealPath("/upload/img");
//           //realpath;;" + realPath);
//            String url = user.getPhoto();
//            System.out.println(url.split("/").length);
//            String relpath5 = realPath + "\\" + url.split("/")[url.split("/").length - 1];
//           //本机路径" + relpath5);
//            user.setPhoto(relpath5);
//            users2.add(user);
//        }
//        EasyExcel.write(fileName, User.class).sheet().doWrite(users2);
       //task李梦琳" + new Date());
    }

}
