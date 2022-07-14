package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors() // WebMvcConfig���� �̹� ���������Ƿ� �⺻ cors ����.
                .and()
                .csrf()// csrf�� ���� ������� �����Ƿ� disable
                .disable()
                .httpBasic()// token�� ����ϹǷ� basic ���� disable
                .disable()
                .sessionManagement()  // session ����� �ƴ��� ����
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // /�� /auth/** ��δ� ���� ���ص� ��.
                .antMatchers("/", "/auth/**","/saram/**","/travinfo/**").permitAll() //������ ��� ��δ� �α����� ���ص� ���
                .anyRequest() // /�� /auth/**�̿��� ��� ��δ� ���� �ؾߵ�.
                .authenticated();

        // filter ���.
        // �� ������Ʈ����
        // CorsFilter ������ �Ŀ�
        // jwtAuthenticationFilter �����Ѵ�.
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
        return http.build();
    }
    // 2022 - 07 -13 ��������Ʈ aws å ���뿡�� �ȵǴ� �κ��� ��α� ���� �ۼ��ؼ� ����
    //������ ��ť��Ƽ ������Ʈ�� ������ �ȵǴ� �κ��� @Bean ó���� ������ ���� �ذ�
    //�α��� ������ basic.disable�� ���� ����
    //���� �ּ�ó�� permiaAll �� ������ �ʿ� ���� ����
}

