package me.nandork.simple.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class HerokuSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    HerokuUserDetailsAuthenticationProvider herokuUserDetailsAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(herokuUserDetailsAuthenticationProvider);
    }
}
