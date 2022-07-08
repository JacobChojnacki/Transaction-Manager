package com.example.springdata_io.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        PasswordEncoder passwordEncoder = passwordEncoderConfig.passwordEncoder();

        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.name, u.password_hash, 1 FROM user_dto u WHERE u.name=?")
                .authoritiesByUsernameQuery("SELECT u.name, u.role, 1 FROM user_dto u WHERE u.name=?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "product").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "product/all").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "order").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "order/all").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.POST, "order").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "customer").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.GET, "customer/all").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST, "admin/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "admin/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "admin/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "admin/customer").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "admin/customer").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "admin/customer").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "admin/order").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "admin/order").hasRole("ADMIN")
                .and()
                .csrf().disable().headers().frameOptions().disable();
    }
}
