package com.csw.monkmanager.controller;


import java.io.File;

/**
 * @author xfcyzq
 * @version 1.0
 * 从各个maven库下载下的jar越来越多，同时由于网络或其他问题导致jar没下载下来，徒留一堆无用非jar文件。另外还有许多自己本地打包的无效版本等等。
 * <p>
 * 现使用如下代码直接将不包含jar包的版本下文件统统删掉。
 */
public class DeleteUselessRepository {

    private static final String MAVEN_PATH = "D:\\[jar]\\Java\\m2";

    //判断是否存在jar

    private static boolean judge(File file) {
        boolean isHaveJar = false;
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File _file : files) {
                if (_file.getName().endsWith(".jar")) {
                    isHaveJar = true;
                }
                if (_file.isDirectory()) {
                    boolean isNextHaveJar = judge(_file);
                    if (isNextHaveJar) {
                        isHaveJar = true;
                    }
                }
            }
        }
        if (!isHaveJar) {
            delete(file);
        }
        return isHaveJar;
    }

   // 删除操作

    public static void delete(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    delete(f);
                }
                f.delete();
               //已删除：" + f.getAbsolutePath());
            }
        } else {
            file.delete();
           //已删除：" + file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        File mavenRoot = new File(MAVEN_PATH);
        if (mavenRoot.exists()) {
            File[] files = mavenRoot.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    judge(file);
                }
            }
        }
    }
}

