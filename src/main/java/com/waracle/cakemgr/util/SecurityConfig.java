package com.waracle.cakemgr.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // disabled for simplicity

        http.oauth2Login().and().oauth2Client();

        http.authorizeRequests()
            .antMatchers("/**").authenticated()
            .antMatchers("/user").permitAll()
            .and().logout().invalidateHttpSession(true).permitAll()
        ;

    }


}
