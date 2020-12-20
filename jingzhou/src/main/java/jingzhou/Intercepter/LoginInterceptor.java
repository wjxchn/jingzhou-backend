package jingzhou.Intercepter;

import jingzhou.POJO.Result;
import jingzhou.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(value = "LoginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!sessionService.isLoggedIn(request)) {
            System.out.println("需要先登录！！！");
            return false;
        }
        return true;
    }
}
