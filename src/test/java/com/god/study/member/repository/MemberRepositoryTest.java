package com.god.study.member.repository;

import com.god.study.member.vo.MemberVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findById() {
        Optional<MemberVo> member = memberRepository.findByMbrId("goddaehee");

        if (member.isPresent()) {
            then("goddaehee").isEqualTo(member.get().getMbrId());
            then("갓대희").isEqualTo(member.get().getMbrNm());
        }


        /*then(!member.isEmpty());

        for(MemberVo vo : member){
            then("goddaehee").isEqualTo(vo.getMbrId());
            then("갓대희").isEqualTo(vo.getMbrNm());
        }*/
    }
}