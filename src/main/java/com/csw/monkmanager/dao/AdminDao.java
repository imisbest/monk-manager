package com.csw.monkmanager.dao;

import com.csw.monkmanager.entity.Admin;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@CacheNamespace
public interface AdminDao extends Mapper<Admin>, DeleteByIdListMapper<Admin, String> {
    List<Admin> qa();

    Admin queryAdminInfo(String primaryPrincipal);

    List<Admin> findByPage(@Param("start") Integer start,
                           @Param("rows") Integer rows);
}
