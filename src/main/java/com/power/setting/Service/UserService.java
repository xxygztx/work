package com.power.setting.Service;

import com.power.setting.domain.GetCare;
import com.power.setting.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int insert(Map map);
    User selectUser(Map map);
    int updateLoginTime(User user);
    List<Map<String, String>> selectFollower();
    List<GetCare> selectCare(List<Map<String, String>> follower);
    int insertCare(Map map);
    List<User> selectPhone(String Phone);
}
