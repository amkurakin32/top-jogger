package com.amkurakin32.topjogger.config;

import com.amkurakin32.topjogger.config.auth.JwtFilter;
import com.amkurakin32.topjogger.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USERS_API = "/restapi/v1/users/**";
    private static final String AUTH_API = "/restapi/v1/auth/**";
    private static final String RUN_LOGS_API = "/restapi/v1/run-log/**";

    private final JwtFilter jwtFilter;

    @Autowired
    public WebSecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_API).permitAll()
                .antMatchers(USERS_API).hasAnyRole(Role.ADMIN.toString(), Role.MANAGER.toString())
                .antMatchers(RUN_LOGS_API).hasAnyRole(Role.ADMIN.toString(), Role.USER.toString())
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}