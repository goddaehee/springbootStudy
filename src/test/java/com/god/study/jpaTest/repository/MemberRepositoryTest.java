package com.god.study.jpaTest.repository;

import com.god.study.jpaTest.vo.MemberVo;
import com.god.study.jpaTest.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findById() {
        List<MemberVo> member = memberRepository.findById("goddaehee");

        then(!member.isEmpty());

        for(MemberVo vo : member){
            then("goddaehee").isEqualTo(vo.getId());
            then("갓대희").isEqualTo(vo.getName());
        }
    }
}