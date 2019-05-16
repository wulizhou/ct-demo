package com.wulizhou.pets.system.config.intercepors;

import com.wulizhou.pets.model.constants.Constants;
import com.wulizhou.pets.service.impl.UserService;
import com.wulizhou.pets.session.LoginUserInfo;
import com.wulizhou.pets.session.LoginUserManager;
import com.wulizhou.pets.system.common.ResultCode;
import com.wulizhou.pets.system.exception.TokenException;
import net.sf.json.JSON;
import org.apache.tomcat.util.bcel.Const;
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
    private LoginUserManager manager;

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.TOKEN);
        if (token != null) {
            LoginUserInfo loginUserInfo = manager.getByLoginToken(token);
            if (null != loginUserInfo) {
                request.setAttribute(Constants.USER, loginUserInfo.getUser());
                return true;
            }
        }
        throw new TokenException();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
