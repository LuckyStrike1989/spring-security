package com.example.myspring.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import com.example.myspring.handler.AuthFailureHandlerImpl;
// import com.example.myspring.handler.SimpleAuthFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public AuthenticationFailureHandler authFailureHandler() {
        return new AuthFailureHandlerImpl();
    }
    // public AuthenticationFailureHandler authFailureHandler() {return new SimpleAuthFailureHandler();}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize     // 접근 권한 설정
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR).permitAll()
                .requestMatchers("/", "/css/**").permitAll()
                .requestMatchers("/system/account/login*").permitAll()
                .anyRequest().authenticated()   // 모든 요청 -> 인증처리
        )
        // 폼 로그인 설정 -> 기본 Spring Security 폼 사용
        // .formLogin(Customizer.withDefaults())
        // 폼 로그인 설정 -> 커스텀 폼 사용
        .formLogin((formLogin) -> formLogin
                // 로그인 페이지 URL
                .loginPage("/system/account/login")
                // 로그인 FORM action URL
                .loginProcessingUrl("/system/account/loginProcess")
                // 로그인 FORM username 변수 이름
                .usernameParameter("memberId")
                // 로그인 FORM password 변수 이름
                .passwordParameter("memberPassword")
                // 인증 실패 후 Redirect(클라이언트를 통해서 이동)할 URL
                //.failureUrl("/system/account/login?error=failure")
                // 인증 실패 후 Forward(서버에서 이동)할 URL
                //.failureForwardUrl("/system/account/loginFailureForward")
                // 인증 실패 후 처리할 핸들러
                .failureHandler(authFailureHandler())
                // URL 허용
                .permitAll()
        )
        // CSRF 사용 설정
        //.csrf(Customizer.withDefaults())
        // CSRF를 사용하지 않음
        .csrf(CsrfConfigurer::disable)
        // HTTP Basic Authentication을 사용하지 않음
        .httpBasic(HttpBasicConfigurer::disable);
        
        return http.build();
    }
}
