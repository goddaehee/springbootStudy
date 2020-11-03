package com.god.study.test.controller;

import com.god.study.test.vo.TestVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

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
        mav.addObject("name", "goddaehee");

        List<String> testList = new ArrayList<String>();
        testList.add("a");
        testList.add("b");
        testList.add("c");

        mav.addObject("list", testList);
        return mav;
    }

}
