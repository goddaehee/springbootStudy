package com.god.study.test.controller;

import com.god.study.test.service.TestService;
import com.god.study.test.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class) // ※ Junit4 사용시
@WebMvcTest(TestRestController.class)
@Slf4j
class TestRestControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private TestService testService;

    @Test
    void getListTest() throws Exception {
        //given
        TestVo testVo = TestVo.builder()
                .id("goddaehee")
                .name("갓대희")
                .build();
        //given
        given(testService.selectOneMember("goddaehee"))
                .willReturn(testVo);

        //when
        final ResultActions actions = mvc.perform(get("/testValue2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("갓대희")))
                .andDo(print());
    }
}