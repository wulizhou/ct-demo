package com.wulizhou.pets.service.facade;

/**
 * @Create in 2019/5/14 22:34
 */
public interface Constants {
	String REDIS_TOKEN_PREFIX="PET_TOKEN_";
	String SMS_PREFIX="SMS_";
	String URL = "http://v.juhe.cn/sms/send";
	String LIKE = "like";
	String UNLIKE = "unlike";
	String COOKIE_NAME = "PET_TOKEN";
	int TOKEN_COOKIE_MAX_AGE = 2592000;
}
