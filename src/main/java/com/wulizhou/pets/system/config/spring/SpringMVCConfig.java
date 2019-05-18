package com.wulizhou.pets.system.config.spring;

import com.wulizhou.pets.system.config.intercepors.LoginInterceptor;
import com.wulizhou.pets.system.config.intercepors.RequestTimeInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * mvc配置
 * @author wulizhou
 */
@Configuration
@ComponentScan(includeFilters={@Filter(classes=RestController.class)})
public class SpringMVCConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// swagger
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/**
	 * Spring inteceptor 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(requestTimeInteceptor()).addPathPatterns("/**");
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**")
				.excludePathPatterns(
						"/user/getVerificationCode",
						"/user/login",
						"/webjars/**",
						"/resources/**",
						"/swagger-ui.html",
						"/swagger-resources/**",
						"/v2/api-docs"
				);
	}

	/**
	 * requestTimeInteceptor
	 */
	@Bean
	public HandlerInterceptor requestTimeInteceptor() {
		return new RequestTimeInteceptor();
	}

	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

}
