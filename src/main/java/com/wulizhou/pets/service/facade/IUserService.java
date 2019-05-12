package com.wulizhou.pets.service.facade;

import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.system.common.IBaseService;

public interface IUserService extends IBaseService<User> {

	String login(User user);
}
