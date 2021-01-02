package com.god.study.member.repository;

import com.god.study.member.vo.MemberRoleVo;
import com.god.study.member.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRoleVo, Long> {
    //@Query("FROM MB_ROLE_BASE WHERE ROLE_NM = :roleNm")
    MemberRoleVo findByRoleNm(@Param("roleNm") String roleNm);
}
