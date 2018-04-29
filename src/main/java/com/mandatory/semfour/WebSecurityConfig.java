package com.mandatory.semfour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                "select username,password,enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "select b.username, a.role_name from roles a, user b where b.username=? and a.id=b.role_id");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .antMatchers().permitAll()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/user/**").access("hasRole(\"ROLE_USER\")")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/loginAgain")
//                .formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
