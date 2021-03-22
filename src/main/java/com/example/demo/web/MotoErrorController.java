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
    public String errorHanding(HttpServletRequest request){
        Object attribute = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (attribute != null){
            int status = Integer.parseInt(attribute.toString());
            if (status == HttpStatus.NOT_FOUND.value()){
                return "error-404";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
