package com.wulizhou.pets.service.facade;

import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.system.common.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
	 * @param request
	 * @return
	 */
	String login(String phone, String code, HttpServletRequest request);

	int like(Integer petId, Integer operation);

	User queryUserByToken(String token);

	Integer collect(Integer id, Integer operation, String type);

	Integer getLikeCount();

	Integer getCollectCount();

	List<Pets> getLike();

	List<Object> getCollect();
}
