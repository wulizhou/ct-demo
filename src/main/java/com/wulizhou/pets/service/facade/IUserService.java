package com.wulizhou.pets.service.facade;

import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.system.common.IBaseService;

import javax.servlet.http.HttpServletRequest;

public interface IUserService extends IBaseService<User> {

	/**
	 * 获取验证码
	 * @param phone
	 * @return
	 */
	String getVerificationCode(String phone);

	/**
	 * 登录
	 * @param phone
	 * @param code
	 * @return
	 */
	String login(String phone, String code);

	int like(Integer petId, String operation, HttpServletRequest request);

	User queryUserByToken(String token);
}
