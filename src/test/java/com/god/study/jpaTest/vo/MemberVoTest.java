package com.god.study.jpaTest.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberVoTest {

    @Test
    void getId() {
        final MemberVo memberVo = MemberVo.builder()
                .id("goddaehee")
                .name("갓대희")
                .build();
        final String id = memberVo.getId();
        assertEquals("goddaehee", id);
    }

    @Test
    void getName() {
        final MemberVo memberVo = MemberVo.builder()
                .id("goddaehee")
                .name("갓대희")
                .build();
        final String name = memberVo.getName();
        assertEquals("갓댐", memberVo.getName());
    }
}