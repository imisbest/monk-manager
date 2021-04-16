package com.csw.monkmanager.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MD5Utils {

    //给定一个字符串
    static String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String getSalt() {
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i <= 7; i++) {
            salt.append(str.charAt(new Random().nextInt(62)));
        }
        return salt.toString();
    }

    public static String getNum() {
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i <= 8; i++) {
            salt.append(str.charAt(new Random().nextInt(10)));
        }
        return salt.toString();
    }

    public static String getPassword(String upassword) {
        return DigestUtils.md5Hex(upassword);
    }
}

