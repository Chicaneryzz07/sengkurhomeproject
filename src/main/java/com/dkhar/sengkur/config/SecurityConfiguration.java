package com.dkhar.sengkur.config;

import com.dkhar.sengkur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .csrf().disable()
                                .authorizeHttpRequests((authorize) ->

                                authorize
                                                .antMatchers("/admin/**",
                                                                "/reports/**", "/endowment/**")
                                                .hasAuthority("ADMIN")
                                                .antMatchers("/**").permitAll()

                                                .anyRequest().authenticated()

                                )

                                .formLogin(
                                                form -> form
                                                                .loginPage("/login")
                                                                .loginProcessingUrl("/login")
                                                                // .defaultSuccessUrl("/reports")
                                                                .permitAll()

                                ).logout(
                                                logout -> logout
                                                                .logoutRequestMatcher(
                                                                                new AntPathRequestMatcher("/logout"))
                                                                .logoutSuccessUrl("/login?logout")
                                                                .invalidateHttpSession(true)
                                                                .deleteCookies("JSESSIONID")

                                                                .permitAll())

                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

                return http.build();
        }

        // @Bean
        // public WebSecurityCustomizer webSecurityCustomizer() {
        // return (web) ->
        // web.ignoring().antMatchers("/index","/","/register","/academics",
        // "/entrepreneurship",
        // "/about","/awards","/felicitations","/saveregistration",
        // "/assets/**"
        // );
        // }

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @Autowired
        private UserService userService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth
                                .userDetailsService(userService)

                                .passwordEncoder(passwordEncoder);

        }

}
