package com.wulizhou.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wulizhou.demo.bean.LogBean;
import com.wulizhou.log.annotation.Log;

/**
 * 日志操作记录测试
 * @author Administrator
 *
 */
@RequestMapping("/demo")
@RestController
public class LogController {

	@GetMapping("/test")
	@ResponseBody
	@Log(type = "增加", value = "增加测试", ignores = {"test"})
	public String llllffff(String test, LogBean haha) {
		System.out.println("这里是测试");
		return "好嗨啊";
	}
	
	@PostMapping("/test")
	@ResponseBody
	@Log(ignores = {"haha.b"})
	public String eeeeaaaa(String test, LogBean haha) {
		System.out.println("这里是测试");
		return "好嗨啊";
	}
	
}
