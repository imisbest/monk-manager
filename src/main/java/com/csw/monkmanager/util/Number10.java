package com.csw.monkmanager.util;

import java.util.Random;

public class Number10 {

    //给定一个字符串
    static String str = "0123456789";

    public static String getNum() {
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            salt.append(str.charAt(new Random().nextInt(10)));
        }
        return salt.toString();
    }

}

