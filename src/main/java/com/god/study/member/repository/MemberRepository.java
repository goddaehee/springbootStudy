package com.god.study.member.repository;


import com.god.study.member.vo.MemberVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long> {
    //비워있어도 잘 작동함.
    // long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함

    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    /*@Query(value = "SELECT  MB.MBR_NO, MB.MBR_NM, MB.MBR_ID, MB.MBR_PWD, MB.REG_MBR_NO, MB.MOD_MBR_NO, MB.REG_TIME, MB.MOD_TIME," +
            " RB.ROLE_NO, RB.ROLE_NM" +
            " FROM  MB_MBR_BASE MB" +
            " INNER JOIN MB_MBR_ROLE_MPPG MPPG" +
            "       ON MB.MBR_NO = MPPG.MBR_NO" +
            " INNER JOIN MB_ROLE_BASE RB" +
            "       ON MPPG.ROLE_NO = RB.ROLE_NO" +
            " WHERE MB.MBR_ID = ?1", nativeQuery = true)*/
    public Optional<MemberVo> findByMbrId(String mbrId);

    public List<MemberVo> findByMbrNm(String name);

    //like검색도 가능
    public List<MemberVo> findByMbrNmLike(String keyword);

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