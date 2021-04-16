package com.csw.monkmanager.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SerializeUtils {

    //序列化对象为字符串
    public static String serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            String string = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //反序列化为对象
    public static Object serializeToObject(String str) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
