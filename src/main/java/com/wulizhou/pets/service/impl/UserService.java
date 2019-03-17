package com.wulizhou.pets.service.impl;

import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.model.mapper.UserMapper;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.BaseMapper;
import com.wulizhou.pets.system.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }
}
