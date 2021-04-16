package com.csw.monkmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by HIAPAD on 2019/10/30.
 */

@SpringBootApplication//替换成2.@ComponentScan
//@EnableAutoConfiguration
@MapperScan("com.csw.monkmanager.dao")
@EnableScheduling//第一种
public class  MonkManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        // System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(MonkManagerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MonkManagerApplication.class);
    }
}
