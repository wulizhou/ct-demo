package com.wulizhou.demo.service;

import javax.servlet.http.HttpServletRequest;

import com.wulizhou.ct.log.autoconfigue.handle.LogHandle;
import com.wulizhou.ct.log.autoconfigue.handle.bean.LogInfo;

/**
 * 使用方法一，实现LogHandle进行操作
 * @author Administrator
 *
 */
//@Service
public class LogService implements LogHandle {

	@Override
	public void notice(LogInfo log, HttpServletRequest request) {
		System.out.println(String.format("[%s]请求方法(%s)，参数信息：%s，执行时间%sms", log.getMethodType(), log.getMethodName(), log.getParam(), log.getExecTime()));
	}

}
