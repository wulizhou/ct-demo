package com.wulizhou.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.wulizhou.log.handle.LogDefaultAdapter;
import com.wulizhou.log.handle.bean.LogInfo;

/**
 * 使用方法二，继承LogDefaultAdapter进行操作，LogDefaultAdpater默认已经打印日志
 * @author Administrator
 *
 */
@Service
public class LogNoticeService extends LogDefaultAdapter {

	@Override
	protected void save(LogInfo log, HttpServletRequest request) {
		System.out.println("这里可以通过request获取操作用户并将记录持久化");
	}
	
}
