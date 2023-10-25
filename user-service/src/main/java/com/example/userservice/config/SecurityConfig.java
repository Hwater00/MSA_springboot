package com.example.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // @Configuration이 붙은 클래스에서 내부 메소드를 한번씩 호출하고 리턴된 자료를 @Bean에 등록
    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception{
//        return http
//                .authorizeRequests() // 인증, 인가 설정을 시작합니다.
//                .requestMatchers("/user-service/health-check").permitAll()// 해당 주소는 인증, 인가 없이 접속 가능하며
//                .anyRequest().hasRole("ADMIN")
//                .and()// 그리고 다음 설정으로
//                .csrf()
//                .disable()
//                .build();

        return http
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/**").permitAll())
                .csrf(csrfConfig -> csrfConfig
                        .disable())
                .build();
    }

    // 암호화 비밀번호 저장을 위한 BCryptEncoder 빈 생성
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}