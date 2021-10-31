package com.ab.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password("&+D7fX'(cV6VKzYz").roles("USER", "ADMIN");

    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST, "/register").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.GET, "/instruments").permitAll()
        .antMatchers(HttpMethod.GET, "/findUser/**").permitAll()
        .antMatchers(HttpMethod.GET, "/*").permitAll()
        .antMatchers(HttpMethod.POST, "/*").permitAll()
        .anyRequest().authenticated();
    }
}