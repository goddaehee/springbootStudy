package com.god.study.jpaTest.controller;

import com.god.study.jpaTest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.core.Is.is;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class) // ※ Junit4 사용시
@SpringBootTest(
        properties = {
                "testId=goddaehee"
                , "testName=갓대희"
        }
)

@Transactional
@AutoConfigureMockMvc
@Slf4j
class TestJpaRestControllerTest {

    @Value("${testId}")
    private String testId;

    @Value("${testName}")
    private String testName;

    @Autowired
    MockMvc mvc;

    @Autowired
    private MemberService memberService;

    @Test
    void getMember() throws Exception{
        log.info("#### Properties 테스트 ####");
        log.info("testId : " + testId);
        log.info("testName : " + testName);

        /******** START : MOC MVC test **********/
        log.info("******** START : MOC MVC test **********");
        mvc.perform(get("/memberTest/28"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is("kimdaehee")))
            .andDo(print());
        log.info("******** END : MOC MVC test **********");
        /******** END : MOC MVC test **********/
    }
}