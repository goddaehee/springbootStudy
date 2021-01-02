package com.god.study.member.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MemberPrincipalVo implements UserDetails {
    private Optional<MemberVo> memberVO;
    //private ArrayList<MemberAuthVo> memberAuthVo;
    public MemberPrincipalVo(Optional<MemberVo> memberAuthes) {
        this.memberVO = memberAuthes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //유저가 갖고 있는 권한 목록
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        /*for(int x=0; x<memberAuthVo.size(); x++) {
            authorities.add(new SimpleGrantedAuthority(memberAuthVo.get(x).getRoleName()));
        }*/
        if (("admin").equals(memberVO.get().getMbrId())) {
            authorities.add(new SimpleGrantedAuthority(MemberRoleEnum.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRoleEnum.MEMBER.getValue()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberVO.get().getMbrPwd();
    }

    @Override
    public String getUsername() {
        return memberVO.get().getMbrId();
    }

    // 계정 만료 여부 반환 (true = 만료되지 않음을 의미)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환 (true = 잠금되지 않음을 의미)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드 만료 여부 반환 (true = 만료되지 않음을 의미)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환 (true = 사용 가능을 의미)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
