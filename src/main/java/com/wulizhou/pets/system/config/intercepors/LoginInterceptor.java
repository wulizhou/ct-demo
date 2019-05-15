package com.wulizhou.pets.system.config.intercepors;

import com.wulizhou.pets.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Create in 2019/5/14 21:39
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	//这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
		/*String token = CookieUtils.getCookieValue(request, Constants.COOKIE_NAME);
		if(StringUtils.isNotBlank(token)){
			User user = userService.queryUserByToken(token);
			if(user != null){
				//已登录，放行；并且设置user，传递到处理器中使用
				request.setAttribute("user", user);
				return true;
			}
		}
		response.sendRedirect("/login");//TODO 需要返回到首页
		return false;*/
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
	}
}
