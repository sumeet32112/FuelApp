package com.noxfilers.fuelApp.config;
import com.noxfilers.fuelApp.security.JwtTokenFilter;
import com.noxfilers.fuelApp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/otpVerification")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/getAllBunkProvider")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/username/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/getVehiclesOfUser/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/vehicles")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/fueling")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/getUserFuelInfo/**/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/complain/**")).permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling().authenticationEntryPoint((request, response, ex) -> { response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage() ); });


        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

