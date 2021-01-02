package com.god.study.member.controller;

import com.god.study.member.service.MemberService;
import com.god.study.member.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    /*
    @Autowired
    MemberService memberService;
    */
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("regist")
    public String memberRegist(MemberVo member) throws Exception {
        memberService.save(member);
        return "redirect:/";
    }
}
