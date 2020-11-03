package com.god.study.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @RequestMapping(value = "/testValue", method = RequestMethod.GET)
    public String getTestValue(){
        String value = "테스트2!";
        return value;
    }
}
