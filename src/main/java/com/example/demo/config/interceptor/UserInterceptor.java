package com.example.demo.config.interceptor;

import com.example.demo.config.ApplicationSecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserInterceptor implements HandlerInterceptor {
    //test for 5 seconds
    //private static final long MAX_INACTIVE_SESSION_TIME = 5 * 1000;
    //for one hour
    private static final long MAX_INACTIVE_SESSION_TIME = 60 * 60000;
    private HttpSession session;
    private ApplicationSecurityConfig applicationSecurityConfig;

    public UserInterceptor(HttpSession session) {
        this.session = session;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (UserInterceptor.isUserLogged()) {
            session = request.getSession();
            System.currentTimeMillis();
            request.getSession().getLastAccessedTime();

            if (System.currentTimeMillis() - session.getLastAccessedTime()
                    > MAX_INACTIVE_SESSION_TIME) {
                SecurityContextHolder.clearContext();
                request.logout();
                response.sendRedirect("/users/signIn");
                return false;
            }
        }
        return true;
    }


    private static boolean isUserLogged() {
        try {
            return !SecurityContextHolder.getContext().getAuthentication()
                    .getName().equals("anonymousUser");
        } catch (Exception e) {
            return false;
        }
    }


}
