package com.god.study.test.mapper;

import com.god.study.test.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<TestVo> selectTest();
}
