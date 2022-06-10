package com.power.setting.Service;

import com.power.setting.domain.User;

import java.util.Map;

public interface UserService {
    int insert(Map map);
    User selectUser(Map map);
    int updateLoginTime(User user);
}
