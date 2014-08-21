package me.nandork.simple.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
class HerokuUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Value("${heroku.app.name}")
    private String appName;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        String password = authentication.getCredentials().toString();

        try {
            HerokuApi herokuApi = new HerokuApi();
            herokuApi.authenticate(username, password);
            if (herokuApi.isCollaborator(appName, username)) {
                return new User(username, password, AuthorityUtils.createAuthorityList("ROLE_COLLABORATOR"));
            }
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        throw new UsernameNotFoundException(username);
    }
}
