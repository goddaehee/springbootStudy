package com.god.study.test.service;

import com.god.study.test.mapper.TestMapper;
import com.god.study.test.vo.TestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TestMapper mapper;

    public List<TestVo> selectTest(){

        logger.trace("TRACE Level 테스트_Service");
        logger.debug("DEBUG Level 테스트_Service");
        logger.info("INFO Level 테스트_Service");
        logger.warn("WARN Level 테스트_Service");
        logger.error("ERROR Level 테스트_Service");

        return mapper.selectTest();
    }
}
