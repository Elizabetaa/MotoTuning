package com.example.demo.config.interceptor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserInterceptor implements HandlerInterceptor {
//    private static final long MAX_INACTIVE_SESSION_TIME = 60 * 6000;
//    private HttpSession session;
//
//    public UserInterceptor(HttpSession session) {
//        this.session = session;
//    }
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (UserInterceptor.isUserLogged()) {
//            session = request.getSession();
//            System.currentTimeMillis();
//            request.getSession().getLastAccessedTime();
//            if (System.currentTimeMillis() - session.getLastAccessedTime()
//                    > MAX_INACTIVE_SESSION_TIME) {
//                SecurityContextHolder.clearContext();
//                request.logout();
//                response.sendRedirect("/users/signIn");
//            }
//        }
//        return true;
//    }
//
//    private static boolean isUserLogged() {
//        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
//    }


}
