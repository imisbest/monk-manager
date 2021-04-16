package com.csw.monkmanager.service;

import java.util.Map;

public interface CountService {
    Map<String,Object> addCount(String title, String uid);

    Map<String,Object> deleteCount(String uid, String id);

    Map<String,Object> updateCount(String uid, String id, String count);
}
