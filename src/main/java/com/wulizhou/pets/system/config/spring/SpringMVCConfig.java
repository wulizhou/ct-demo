package com.wulizhou.pets.system.config.spring;

import com.wulizhou.pets.system.config.intercepors.LoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * mvc配置
 * @author wulizhou
 */
@Configuration
@ComponentScan(includeFilters={@Filter(classes=RestController.class)})
public class SpringMVCConfig extends WebMvcConfigurationSupport {
	
	/**
	 * Spring inteceptor 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(requestTimeInteceptor());
		registry.addInterceptor(loginInterceptor());
	}

	/**
	 * requestTimeInteceptor
	 */
	public HandlerInterceptor requestTimeInteceptor() {
		String[] includePatterns = { "/**" };
		return new MappedInterceptor(includePatterns, new RequestTimeInteceptor());
	}

	public HandlerInterceptor loginInterceptor() {
		String[] includePatterns = { "/**" };
		return new MappedInterceptor(includePatterns, new LoginInterceptor());
	}
	
}
