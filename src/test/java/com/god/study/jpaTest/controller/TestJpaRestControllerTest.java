package com.god.study.jpaTest.controller;

import com.god.study.jpaTest.repository.MemberRepository;
import com.god.study.jpaTest.service.MemberService;
import com.god.study.jpaTest.vo.MemberVo;
import com.god.study.test.service.TestService;
import com.god.study.test.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.core.Is.is;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class) // ※ Junit4 사용시
@SpringBootTest(
        properties = {
                "testId=goddaehee"
                , "testName=갓대희"
        }
        ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
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

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach() //Junit4의 @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
            .addFilters(new CharacterEncodingFilter("UTF-8", true)) // 필터 추가
            .alwaysDo(print())
            .build();
    }

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private TestService testService;

    @Autowired
    private TestRestTemplate restTemplate;


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

        /******** START : TestRestTemplate test **********/
        log.info("******** START : TestRestTemplate test **********");
        ResponseEntity<MemberVo> response = restTemplate.getForEntity("/memberTest/28", MemberVo.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        log.info("******** END : TestRestTemplate test **********");
        /******** END : TestRestTemplate test **********/
    }

    @Test
    void getMember2() throws Exception {
        /******** START : MockBean test **********/
        log.info("******** START : MockBean test **********");
        MemberVo memberVo = MemberVo.builder()
                .id("goddaehee2")
                .name("갓대희")
                .build();

        given(memberRepository.findById(28L))
                .willReturn(Optional.of(memberVo));

        Optional<MemberVo> member = memberService.findById(28L);
        if (member.isPresent()) {
            // ※ Junit4 사용시
            // assertThat(memberVo.getId()).isEqualTo(member.get().getId());
            // assertThat(memberVo.getName()).isEqualTo(member.get().getName());

            // Junit5 BDD 사용시
            then("goddaehee").isEqualTo(member.get().getId());
            then("갓대희").isEqualTo(member.get().getName());
        }
        log.info("******** END : MockBean test **********");
        /******** END : MockBean test **********/
    }

    @Test
    void getListTest() throws Exception {
        //given
        TestVo testVo = TestVo.builder()
                .id("kim,daehee")
                .name("갓대희")
                .build();
        //given
        given(testService.selectOneMember("kimdaehee"))
                .willReturn(testVo);

        //when
        final ResultActions actions = mvc.perform(get("/testValue2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("갓대희")))
                .andDo(print());
    }
}