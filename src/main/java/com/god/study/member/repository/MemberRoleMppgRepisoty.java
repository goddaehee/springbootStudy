package com.god.study.member.repository;

import com.god.study.member.vo.MemberRoleMppgVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRoleMppgRepisoty extends JpaRepository<MemberRoleMppgVo, Long> {

}
