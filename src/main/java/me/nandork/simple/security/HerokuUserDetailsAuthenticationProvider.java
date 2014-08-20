package me.nandork.simple.security;

import me.nandork.simple.security.model.Collaborator;
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

import java.io.IOException;
import java.util.List;

@Component
class HerokuUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Value("${heroku.app.name: try-spring-boot}")
    private String appName;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        String password = authentication.getCredentials().toString();

        List<Collaborator> collaborators;
        try {
            HerokuApi herokuApi = new HerokuApi();
            herokuApi.authenticate(username, password);
            collaborators = herokuApi.getCollaborators(appName);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        for (Collaborator collaborator : collaborators) {
            if (collaborator.getUser().getEmail().equals(username)) {
                return new User(username, password, AuthorityUtils.createAuthorityList("COLLABORATOR"));
            }
        }

        throw new UsernameNotFoundException(username);
    }
}
