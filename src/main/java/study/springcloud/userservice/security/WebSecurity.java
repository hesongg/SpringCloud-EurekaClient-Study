package study.springcloud.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import study.springcloud.userservice.service.UserService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity {
    //WebSecurityConfigurerAdapter 가 deprecated 되었으므로 강의와 다른 방식으로 작업

    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthenticationManagerBuilder authBuilder;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Environment environment;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll(); // 모든 요청 허용
        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress("210.210.213.227")
                .and()
                .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();

        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    // select pwd from users where email=?
    // db_pwd(encrypted) == input_pwd(encrypted)
    private AuthenticationManager authenticationManager() throws Exception {
        return authBuilder.userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
