package com.works.configs;

import com.works.services.AdminService;
import com.works.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    final PasswordEncoder passwordEncoder;
    final AdminService adminService;
    final UserService userService;
    //sql -> login,role control
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    //security request -> role -> mapping
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
        .httpBasic()
        .and()
        .authorizeHttpRequests()

         /*.antMatchers("/product/update").hasRole("admin")
         .antMatchers("/product/save").hasRole("admin")
         .antMatchers("/product/delete/{cid}").hasRole("admin")
        .antMatchers("/category/save").hasRole("admin")
        .antMatchers("/category/delete/{cid}").hasRole("admin")
        .antMatchers("/category/update").hasRole("admin")
        .antMatchers("/orders/list").hasRole("admin")*/
                .antMatchers("**/**").permitAll()
        .and()
        .csrf().disable().formLogin().disable();//security form disable
    }
}
