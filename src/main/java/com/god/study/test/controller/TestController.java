package com.god.study.test.controller;

import com.god.study.test.service.TestService;
import com.god.study.test.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Controller
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TestService testService;

    @RequestMapping(value = "/home")
    public String home() {

        return "index.html";
    }

    @ResponseBody
    @RequestMapping(value = "/valueTest")
    public String valueTest() {
        String value = "테스트!";
        return value;
    }

    @RequestMapping("/thymeleafTest")
    public String thymeleafTest(Model model) {
        TestVo testModel = new TestVo("goddaehee", "갓대희");
        model.addAttribute("testModel", testModel);
        return "thymeleaf/thymeleafTest";
    }

    @RequestMapping("/test")
    public ModelAndView test() throws Exception {
        ModelAndView mav = new ModelAndView("test");

        /*// 시큐리티 컨텍스트 객체를 얻습니다.
        SecurityContext context = SecurityContextHolder.getContext();
        // 인증 객체를 얻습니다.
        Authentication authentication = context.getAuthentication();
        // 로그인한 사용자정보를 가진 객체를 얻습니다.
        Principal principal = (Principal) authentication.getPrincipal();

        // 사용자가  가진 모든 롤 정보를 얻습니다.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Iterator<? extends GrantedAuthority> iter = authorities.iterator();

        while (iter.hasNext()) { GrantedAuthority auth = iter.next(); log.info(auth.getAuthority()); }*/

        /*
        mav.addObject("name", "goddaehee");

        List<String> testList = new ArrayList<String>();
        testList.add("a");
        testList.add("b");
        testList.add("c");*/

        List<TestVo> testList = testService.selectTest();
        mav.addObject("list", testList);

        logger.trace("TRACE Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("WARN Level 테스트");
        logger.error("ERROR Level 테스트");

        return mav;
    }

}
