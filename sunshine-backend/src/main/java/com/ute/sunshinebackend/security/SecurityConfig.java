package com.ute.sunshinebackend.security;

import com.ute.sunshinebackend.service.CustomUserService;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig { //extends WebSecurityConfigurerAdapter

    @Autowired
    CustomUserService userService;

    @Autowired
    JWTTokenHelper jwtTokenHelper;


    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//    @Bean
//    protected void configure(HttpSecurity http) throws Exception {
//        //http.authorizeRequests((request)->request.antMatchers("/api/v1/*").permitAll().anyRequest().authenticated()).httpBasic();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
//                .authorizeRequests((request)->request.antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/api/info/**").permitAll()
//                .antMatchers(String.valueOf(HttpMethod.OPTIONS), "/**").permitAll()
//                .anyRequest().authenticated())
//                .addFilterBefore(new JWTAuthenticationFilter(userService, jwtTokenHelper),
//                        UsernamePasswordAuthenticationFilter.class);
//
//        http.formLogin();
//
//        http.csrf().disable().cors().and().headers().frameOptions().disable();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/info/**").permitAll()
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(new JWTAuthenticationFilter(userService, jwtTokenHelper),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("hang").password(passwordEncoder().encode("abc123")).authorities("USER", "ADMIN");
//
//        //Database auth
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
