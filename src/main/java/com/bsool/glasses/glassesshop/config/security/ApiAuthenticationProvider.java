package com.bsool.glasses.glassesshop.config.security;

import com.bsool.glasses.glassesshop.data.user.EntityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private final ApiAuthenticationService apiAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication.isAuthenticated()) {
            if (!ApiAuthenticationToken.class.equals(authentication.getClass())) {
                return authentication;
            }
        }
        String clientKey = authentication.getPrincipal().toString();
        String clientSecret = authentication.getCredentials().toString();

        final EntityUser user = apiAuthenticationService.findByUsernameAndPassword(clientKey, clientSecret);

        final ApiAuthenticationToken apiAuthenticationToken = new ApiAuthenticationToken(clientKey, clientSecret, getAuthorities(user));
        apiAuthenticationToken.setUser(user);
        return apiAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final EntityUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        return authorities;

    }
}
