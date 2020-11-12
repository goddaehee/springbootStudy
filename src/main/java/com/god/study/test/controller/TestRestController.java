package com.god.study.test.controller;

import com.god.study.test.service.TestService;
import com.god.study.test.vo.TestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TestService testService;

    @RequestMapping(value = "/testValue", method = RequestMethod.GET)
    public String getTestValue(){
        String value = "테스트2!";
        return value;
    }


    @RequestMapping(value="/testValue2", method = RequestMethod.GET)
    public TestVo testValue2() throws Exception {

        logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");

        TestVo testResult = testService.selectOneMember("kimdaehee");

        return testResult;
    }
}
