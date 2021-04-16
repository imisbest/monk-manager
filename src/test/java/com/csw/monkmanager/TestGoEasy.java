package com.csw.monkmanager;

import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by HIAPAD on 2019/12/2.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestGoEasy {
    @Test
    public void test() {
        // 查询数据 list
        // 把list集合转换为 json字符串
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-8c7f344f2ffc4974a88531acc725c2f9");
        goEasy.publish("csw", "Hello World!");//content : json字符串
    }
}
