package com.god.study.config;

import com.god.study.member.service.MemberService;
import com.god.study.member.vo.MemberRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 해당 경로의 파일들은 Spring Security가 무시할 수 있도록 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                 "/css/**"
                //,"/static/**"
                , "/js/**"
                , "/img/**"
                , "/lib/**"
                , "/docs/**"
                , "/error"
                , "/oauth2/**"
                , "/h2-console/**"
                , "/profile"
        );
    }

    // HttpSecurity를 통해 HTTP 요청에 대한 보안을 설정할 수 있다.
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests() //HttpServletRequest 요청 URL에 따라 접근 권한을 설정, 접근(access)을 제한, 허가 등
                .antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/test/**").hasAnyRole(MemberRoleEnum.ADMIN.getValue(), MemberRoleEnum.MEMBER.getValue())
                .antMatchers("/test/**").hasAnyRole("ADMIN", "MEMBER")
                .antMatchers(HttpMethod.DELETE, "/member/**").hasRole("ADMIN")
                .antMatchers("/**", "/other/%%").permitAll()
                //.anyRequest().authenticated() // 그 외 모든 요청은 인증정보 필요함.
                .and()
            .formLogin()
                //.loginPage("/login/login")
                .usernameParameter("mbrId")
                .passwordParameter("mbrPwd")
                //.defaultSuccessUrl("/")
                .failureUrl("/login/login")
                .successHandler(successHandler())
                //.failureHandler(failureHandler())
                .permitAll()
                .and()
            .logout()
                //.logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                .logoutSuccessUrl("/login/logout/result")
                .invalidateHttpSession(true)
                .and()
            // 403 예외처리 핸들링
            .exceptionHandling().accessDeniedPage("/login/denied")
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint());
                /*.and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    // SecurityConfig.java에 successHandler를 등록하고, 빈 형태로 등록해두면 된다.
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/");//default로 이동할 url
    }

    // 인증 실패 메세징 처리
    @Bean
    public CustomBasicAuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

}
