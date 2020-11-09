package com.god.study.jpaTest.service;

import com.god.study.jpaTest.repository.MemberRepository;
import com.god.study.jpaTest.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class MemberService {
    private RestTemplate restTemplate;

    public MemberService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    private MemberRepository memberRepository;

    public MemberVo getMember(Long mbrNo) {
        MemberVo response = restTemplate.getForObject("/memberTest/" + mbrNo, MemberVo.class);
        log.info("getMember2 : " + response);
        return response;
    }

    public List<MemberVo> findAll() {
        List<MemberVo> members = new ArrayList<>();
        memberRepository.findAll().forEach(e -> members.add(e));
        return members;
    }

    public Optional<MemberVo> findById(Long mbrNo) {
        Optional<MemberVo> member = memberRepository.findById(mbrNo);
        return member;
    }

    public void deleteById(Long mbrNo) {
        memberRepository.deleteById(mbrNo);
    }

    public MemberVo save(MemberVo member) {
        memberRepository.save(member);
        return member;
    }

    public void updateById(Long mbrNo, MemberVo member) {
        Optional<MemberVo> e = memberRepository.findById(mbrNo);

        if (e.isPresent()) {
            e.get().setMbrNo(member.getMbrNo());
            e.get().setId(member.getId());
            e.get().setName(member.getName());
            memberRepository.save(member);
        }
    }

    /*@Override
    public List<Member> findBySalBetween(int sal1, int sal2) {
        List<Member> emps = memberRepository.findBySalBetween(sal1, sal2);
        System.out.println(emps.size() + ">>>>>>>>>>>>>>>>" + sal1 + sal2);
        if (emps.size() > 0)
            return emps;
        else
            return null;
    }*/
}
