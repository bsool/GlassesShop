package com.bsool.glasses.glassesshop.config.security;

import com.bsool.glasses.glassesshop.data.user.EntityUser;
import com.bsool.glasses.glassesshop.data.user.EntityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiAuthenticationService {

    private final EntityUserRepository userRepository;

    public EntityUser getClientEntity() {
        return ((ApiAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
    }

    public EntityUser findByUsernameAndPassword(final String username, final String password) {
        return userRepository.findByUsernameIgnoreCaseAndPassword(username, password).orElseThrow(() -> new ApiAuthenticationException("user not found"));
    }
}
