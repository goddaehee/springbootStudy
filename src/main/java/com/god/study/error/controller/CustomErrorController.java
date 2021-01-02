package com.god.study.error.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {
    private String VIEW_PATH = "thymeleaf/error/";

    /*@RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object status2 = request.getAttribute("javax.servlet.error.status_code");

        Object exceptionObj = request.getAttribute("javax.servlet.error.exception");
        Object exceptionObj2 = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if(exceptionObj != null){
            Throwable e = ((Exception) exceptionObj2).getCause();
            model.addAttribute("exception",e.getClass().getName());
            model.addAttribute("message",e.getMessage());
        }

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return VIEW_PATH + "404";
            }
            if(statusCode == HttpStatus.FORBIDDEN.value()){
                return VIEW_PATH + "500";
            }
        }
        return "error";
    }*/

    /*@RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(response.getStatus());

        Object exceptionObj = request.getAttribute("javax.servlet.error.exception");
        Object exceptionObj2 = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if(exceptionObj2 != null){
            Throwable e = ((Exception) exceptionObj2).getCause();
            //model.addAttribute("exception",e.getClass().getName());
            //model.addAttribute("message",e.getMessage());

            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }


        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            modelAndView.addObject("statusCode", statusCode);
            *//*Optional<Object> exception = (Optional<Object>) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
            Optional<Object> request_uri= (Optional<Object>) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
            Optional<Object> servlet_name= (Optional<Object>) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
            Optional<Object> message= (Optional<Object>) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);*//*

            *//*if (exception.isPresent()) {
                modelAndView.addObject("exception", exception.get().toString());
            }

            if (request_uri.isPresent()) {
                modelAndView.addObject("request_uri", request_uri.get().toString());
            }*//*

            *//*modelAndView.addObject("exception", (Object)(RequestDispatcher.ERROR_EXCEPTION).toString());
            modelAndView.addObject("request_uri", RequestDispatcher.FORWARD_REQUEST_URI.toString());
            modelAndView.addObject("servlet_name", RequestDispatcher.ERROR_SERVLET_NAME.toString());
            modelAndView.addObject("message", RequestDispatcher.ERROR_MESSAGE.toString());*//*

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                modelAndView.setViewName(VIEW_PATH + "404");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelAndView.setViewName("/errorPage/500");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                modelAndView.setViewName("/errorPage/403");
            } else modelAndView.setViewName("/errorPage/common");
        }
        return modelAndView;
    } */

    @Override
    /* 스프링 부트 2.3.x부터 다음 메서드는 deprecated 되었음.
    *  custom path를 지정하려면 server.error.path 속성으로 지정 가능.
    *  */
    public String getErrorPath() {
        return null;
    }
}
