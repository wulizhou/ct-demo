package com.wulizhou.pets.session;

import com.wulizhou.pets.model.entity.User;

/**
 * 用户登录信息包装类
 */
public class LoginUserInfo {

	private User user;

	private String token;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
