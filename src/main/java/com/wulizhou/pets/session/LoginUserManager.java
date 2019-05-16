package com.wulizhou.pets.session;

import com.wulizhou.pets.model.constants.Constants;
import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录信息管理类<br>
 * 可由userId、token来操作登录信息，在维护时，也需要对userId、token两方面维护
 * @author wulizhou
 *
 */
@Component
public class LoginUserManager {

	/**
	 * 登录有效期
	 */
	private static final long LOGIN_EXPIRE = 30L * 24 * 3600;

	@Autowired
	private RedisService redisService;

	/**
	 * 存放登录数据
	 * 
	 * @param token
	 * @param loginUser
	 */
	public void put(String token, LoginUserInfo loginUser) {
		if (StringUtils.isNotBlank(token)) {
			redisService.set(Constants.REDIS_TOKEN_PREFIX + token, loginUser, LOGIN_EXPIRE);
			redisService.set(Constants.REDIS_TOKEN_PREFIX + loginUser.getUser().getUserId(), loginUser, LOGIN_EXPIRE);
		}
	}

	/**
	 * 存放登录数据
	 * 
	 * @param userId
	 * @param loginUserInfo
	 */
	public void put(Integer userId, LoginUserInfo loginUserInfo) {
		if (null != userId) {
			redisService.set(Constants.REDIS_TOKEN_PREFIX + userId, loginUserInfo, LOGIN_EXPIRE);
			redisService.set(Constants.REDIS_TOKEN_PREFIX + loginUserInfo.getToken(), loginUserInfo, LOGIN_EXPIRE);
		}
	}

	/**
	 * 根据loginToken删除 指定 key的对象
	 * @param token
	 * @return
	 */
	public LoginUserInfo deleteByLoginToken(String token) {
		if (StringUtils.isNotBlank(token)) {
			LoginUserInfo userInfo = this.getByLoginToken(token);
			if (null != userInfo) {
				redisService.remove(Constants.REDIS_TOKEN_PREFIX + token);
				if(null != userInfo.getUser()){
					redisService.remove(Constants.REDIS_TOKEN_PREFIX + userInfo.getUser().getUserId());
				}
				return userInfo;
			}
		}
		return null;
	}
	
	/**
	 * 根据用户标识删除用户登录信息
	 * @param userId
	 * @return
	 */
	public LoginUserInfo deleteByUserId(Integer userId){
		if(null != userId){
			LoginUserInfo loginUserInfo = redisService.get(Constants.REDIS_TOKEN_PREFIX + userId);
			if(null != loginUserInfo){
				redisService.remove(Constants.REDIS_TOKEN_PREFIX + userId);
				if(null != loginUserInfo.getToken()){
					String token = loginUserInfo.getToken();
					redisService.remove(Constants.REDIS_TOKEN_PREFIX + token);
				}
			}
			return loginUserInfo;
		}
		return null;
	}

	/**
	 * 根据loginToken获取对象信息
	 * @param token
	 * @return
	 */
	public LoginUserInfo getByLoginToken(String token) {
		if (StringUtils.isNotBlank(token)) {
			LoginUserInfo loginUserInfo = redisService.get(Constants.REDIS_TOKEN_PREFIX + token);
			return loginUserInfo;
		}
		return null;
	}
	
	/**
	 * 根据用户标识获取用户登录信息
	 * @param userId
	 * @return
	 */
	public LoginUserInfo getByUserId(Integer userId){
		
		LoginUserInfo loginUserInfo = null;
		if(null != userId){
			loginUserInfo = redisService.get(Constants.REDIS_TOKEN_PREFIX + userId);
		}
		return loginUserInfo;
	}
	
	/**
	 * 根据loginToken获取用户信息
	 * @param token
	 * @return
	 */
	public User getUserByLoginToken(String token){
		if (StringUtils.isNotBlank(token)) {
			LoginUserInfo loginUserInfo = redisService.get(Constants.REDIS_TOKEN_PREFIX + token);
			if (null != loginUserInfo) {
				return loginUserInfo.getUser();
			}
		}
		return null;
	}
	
	/**
	 * 根据userId获取用户信息
	 * @param userId
	 * @return
	 */
	public User getUserByUserId(Integer userId){
		
		if(null != userId){
			LoginUserInfo loginUserInfo = redisService.get(Constants.REDIS_TOKEN_PREFIX + userId);
			if(null != loginUserInfo){
				return loginUserInfo.getUser();
			}
		}
		return null;
	}
	
}
