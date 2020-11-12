package com.god.study.test.mapper;

import com.god.study.test.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<TestVo> selectTest();

    int deleteData(HashMap<Object, Object> vo) throws Exception;

    int updateData(HashMap<Object, Object> vo) throws Exception;

    int insertData(HashMap<Object, Object> vo) throws Exception;

    List<HashMap<Object, Object>> selectData(HashMap<Object, Object> vo);

    TestVo selectOneMember(String id) throws Exception;
}
