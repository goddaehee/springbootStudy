package com.god.study.test.service;

import com.god.study.test.mapper.TestMapper;
import com.god.study.test.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    public TestMapper mapper;

    public List<TestVo> selectTest(){
        return mapper.selectTest();
    }
}
