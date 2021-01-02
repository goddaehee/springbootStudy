package com.god.study.member.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberVoTest {

    @Test
    void getId() {
        final MemberVo memberVo = MemberVo.builder()
                .mbrId("goddaehee")
                .mbrNm("갓대희")
                .build();
        final String id = memberVo.getMbrId();
        assertEquals("goddaehee", id);
    }

    @Test
    void getName() {
        final MemberVo memberVo = MemberVo.builder()
                .mbrId("goddaehee")
                .mbrNm("갓대희")
                .build();
        final String name = memberVo.getMbrNm();
        assertEquals("갓댐", memberVo.getMbrNm());
    }
}