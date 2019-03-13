package com.wulizhou.pets.service.impl;

import com.wulizhou.pets.model.mapper.UserMapper;
import com.wulizhou.pets.service.facade.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

}
