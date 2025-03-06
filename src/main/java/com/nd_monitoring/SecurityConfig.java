package com.nd_monitoring;
import com.nd_monitoring.component.AccDeniedHandle;
import com.nd_monitoring.component.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccDeniedHandle accDeniedHandle;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(authorize -> authorize
                        //기타 정적 리소스 경로에 대해 허용
                        .requestMatchers("/resources/**", "/static/**").permitAll()
                        .requestMatchers("/**" //모든페이지 허용
                        ).permitAll()
                        .requestMatchers("/users/**").authenticated() //인증된 사용자만 접근 가능함
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") //사용자 정의 PostMapping 로그인 페이지
                        .defaultSuccessUrl("/", true) //로그인 성공 후 이동할 페이지
                        .failureHandler(customAuthenticationFailureHandler) // 로그인 실패
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            // 이미 로그인된 사용자가 로그인 페이지에 접근할 경우 메인 페이지로 리다이렉트
                            response.sendRedirect("/");
                        })
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃을 받을 GetMapping
                        .logoutSuccessUrl("/") //로그아웃 완료 후 이동할 페이지
                        .invalidateHttpSession(true)) //세션 해제
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accDeniedHandle)) //특정페이지에 권한문제로 접속하지 못할경우 accDeineHandle 클래스 실행
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/api/xxx"


                        ) // 특정 경로에 대해 CSRF 비활성화
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
