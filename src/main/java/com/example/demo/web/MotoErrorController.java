package com.example.demo.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MotoErrorController implements ErrorController {

    @RequestMapping("/error")
    public String errorHanding(HttpServletRequest request) {
        Object attribute = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (attribute != null) {
            int status = Integer.parseInt(attribute.toString());
            if (status == HttpStatus.NOT_FOUND.value()) {
                return "error_404";
            } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error_500";
            } else if (status == HttpStatus.FORBIDDEN.value()) {
                return "error_403";
            }
        }
        System.out.println(Integer.parseInt(attribute.toString()));
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
