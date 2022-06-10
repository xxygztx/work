package com.power.setting.mapper;

import com.power.setting.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    int insert(Map map);
    User selectUser(Map map);
    int updateLoginTime(User user);
    int insertSelective(User record);
}