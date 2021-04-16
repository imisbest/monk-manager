package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    List<Guru> selectAll();

    Map<String,Object> add(Guru guru);

    Map<String,Object> update(Guru guru);

    Map<String,Object> delete(String[] id);

    void updateByPrimaryKeySelective(Guru guru);
}
