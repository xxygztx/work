package com.power.setting.mapper;

import com.power.setting.domain.GetCare;
import com.power.setting.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int insert(Map map);
    User selectUser(Map map);
    int updateLoginTime(User user);
    int insertSelective(User record);
    List<Map<String, String>> selectFollower();
    List<GetCare> selectCare(List<Map<String, String>> follower);
    int insertCare(Map map);
    List<User> selectPhone(String Phone);
}