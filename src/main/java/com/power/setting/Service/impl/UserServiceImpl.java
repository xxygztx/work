package com.power.setting.Service.impl;

import com.power.setting.Service.UserService;
import com.power.setting.domain.User;
import com.power.setting.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public int insert(Map map) {
        return userMapper.insert(map);
    }

    @Override
    public User selectUser(Map map) {
       return userMapper.selectUser(map);
    }

    @Override
    public int updateLoginTime(User user) {
        return userMapper.updateLoginTime(user);
    }
}
