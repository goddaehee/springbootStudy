package com.god.study.test.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/*@Builder*/
@Getter
@Setter
@ToString
@Slf4j
public class TestVo {
    private Long mbrNo;
    private String id;
    private String name;

    public TestVo() {
    }

    public TestVo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(Long mbrNo) {
        this.mbrNo = mbrNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
