package com.csw.monkmanager.dao;

import com.csw.monkmanager.entity.MapVO;
import com.csw.monkmanager.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User>, DeleteByIdListMapper<User, String> {
    Integer find1(@Param("sex1") String sex1, @Param("s") String s);

    Integer find2(@Param("sex2") String sex2, @Param("s") String s);

    List<MapVO> showAllMap(@Param("sex1") String sex1);

    User selectByAB(User user);
}
