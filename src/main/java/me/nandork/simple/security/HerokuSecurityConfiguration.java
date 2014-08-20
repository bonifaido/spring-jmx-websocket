package me.nandork.simple.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * See: http://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#multiple-httpsecurity
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class HerokuSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private HerokuUserDetailsAuthenticationProvider herokuUserDetailsAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/manage/**")
                .authorizeRequests()
                .anyRequest().hasRole("COLLABORATOR")
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(herokuUserDetailsAuthenticationProvider);
    }
}
