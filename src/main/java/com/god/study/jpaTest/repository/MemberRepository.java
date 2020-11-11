package com.god.study.jpaTest.repository;


import com.god.study.jpaTest.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long> {
    //비워있어도 잘 작동함.
    // long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함

    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    public List<MemberVo> findById(String id);

    public List<MemberVo> findByName(String name);

    //like검색도 가능
    public List<MemberVo> findByNameLike(String keyword);

    // 쿼리 메소드, 메소드 이름으로 자동으로 SELECT 쿼리 생성
    // JPA에서 자동으로 생성하는 쿼리는 다음과 같다.
    // select
    // emp0_.empno as empno1_0_,
    // emp0_.ename as ename2_0_,
    // emp0_.sal as sal3_0_
    // from
    // emp emp0_
    // where
    // emp0_.sal between ? and ?

    // List<Member> findBySalBetween(int sal1, int sal2);
}