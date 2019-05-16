package com.wulizhou.pets.session;

import com.wulizhou.pets.model.constants.Constants;
import com.wulizhou.pets.model.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionUtil {

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static User getCurrentUser() {
        HttpServletRequest request = getRequest();
        return (User)request.getAttribute(Constants.USER);
    }

    public static Integer getCurrentUserId() {
        User user = getCurrentUser();
        return null == user ? null : user.getUserId();
    }

}
