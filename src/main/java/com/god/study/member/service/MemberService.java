package com.god.study.member.service;

import com.god.study.member.repository.MemberRepository;
import com.god.study.member.repository.MemberRoleMppgRepisoty;
import com.god.study.member.repository.MemberRoleRepository;
import com.god.study.member.vo.MemberRoleEnum;
import com.god.study.member.vo.MemberRoleMppgVo;
import com.god.study.member.vo.MemberRoleVo;
import com.god.study.member.vo.MemberVo;
import com.sun.istack.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class MemberService implements UserDetailsService {
    private RestTemplate restTemplate;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final MemberRoleMppgRepisoty memberRoleMppgRepisoty;

    public MemberService(RestTemplateBuilder restTemplateBuilder, MemberRepository memberRepository, MemberRoleRepository memberRoleRepository, MemberRoleMppgRepisoty memberRoleMppgRepisoty) {
        this.restTemplate = restTemplateBuilder.build();
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
        this.memberRoleMppgRepisoty = memberRoleMppgRepisoty;
    }

    // 상세 정보를 조회하는 메서드, 사용자의 계정정보, 권한을 갖는 UserDetails 인터페이스를 반환해야 한다.
    // 매개변수 : 로그인 시 입력한 아이디 (Spring Security에서는 기본적으로 username라는 이름을 사용)
    @Override
    public UserDetails loadUserByUsername(String mbrId) throws UsernameNotFoundException {
        Optional<MemberVo> memberVoWrapper = memberRepository.findByMbrId(mbrId);
        List<MemberRoleMppgVo> memberRoleMppgVosList;

        if(!memberVoWrapper.isPresent()) {
            throw new UsernameNotFoundException("User : "+mbrId+" Not Found!");
        }else{
            MemberVo memberVo = memberVoWrapper.get();
            memberRoleMppgVosList = memberVoWrapper.get().getMemberRoleMppgVos();

            List<GrantedAuthority> authorities = new ArrayList<>();

            memberRoleMppgVosList.forEach(x->{
                //log.info(x.getRole().getRoleNm());
                authorities.add(new SimpleGrantedAuthority(x.getRole().getRoleNm()));
            });

            /*if (("ADMIN").equals(mbrId) || ("admin").equals(mbrId)) {
                authorities.add(new SimpleGrantedAuthority(MemberRoleEnum.ADMIN.getValue()));
                //authorities.add(new SimpleGrantedAuthority(MemberRoleEnum.MEMBER.getValue()));
            } else {
                authorities.add(new SimpleGrantedAuthority(MemberRoleEnum.MEMBER.getValue()));
            }*/

            /*Optional.ofNullable(memberRepository.findByMbrId(mbrId))
                    .filter(m -> m!= null)
                    .map(m -> new SecurityMember(m)).get();*/

            return new User(memberVoWrapper.get().getMbrId(), memberVoWrapper.get().getMbrPwd(), authorities);
        }
    }

    @Transactional
    public MemberVo save(MemberVo member) throws Exception {
        Optional<MemberVo> findMember = memberRepository.findByMbrId(member.getMbrId());

        if (findMember.isPresent()) {
            System.out.println("Duplicated :: " + findMember.toString() );
            throw new Exception("userid already taken");
        }

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setMbrPwd(passwordEncoder.encode(member.getMbrPwd()));

        memberRepository.save(member);

        // 롤 조회하여 기본 Member 권한 입력
        MemberRoleVo role = memberRoleRepository.findByRoleNm(MemberRoleEnum.MEMBER.getValue());
        MemberRoleMppgVo memberRoleMppgVos;
        if(role != null){
            memberRoleMppgVos = new MemberRoleMppgVo(member, role.getRoleNo());
            memberRoleMppgRepisoty.save(memberRoleMppgVos);
        }

        /*member.setMemberRoleMppgVos(Arrays.asList(memberRoleMppgVos));*/

        /* N:M 풀때 */
        /*member.setRoles(Arrays.asList(role));*/
        return member;
    }

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

    public void updateById(Long mbrNo, MemberVo member) {
        Optional<MemberVo> e = memberRepository.findById(mbrNo);

        if (e.isPresent()) {
            e.get().setMbrNo(member.getMbrNo());
            e.get().setMbrId(member.getMbrId());
            e.get().setMbrNm(member.getMbrNm());
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
