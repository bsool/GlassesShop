package com.bsool.glasses.glassesshop.config.security;

import com.bsool.glasses.glassesshop.data.user.EntityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class ApiAuthenticationFilter extends OncePerRequestFilter {


    private final ApiAuthenticationService apiAuthenticationService;
    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
    private final AuthenticationManager authenticationManager;
    private final RequestMatcher requiresAuthenticationRequestMatcher;
    private final SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();
    private final RememberMeServices rememberMeServices = new NullRememberMeServices();

    public ApiAuthenticationFilter(AuthenticationManager authenticationManager, ApiAuthenticationService apiAuthenticationService) {
        this.requiresAuthenticationRequestMatcher = new AntPathRequestMatcher("/rest/**");
        this.authenticationManager = authenticationManager;
        this.apiAuthenticationService = apiAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (this.requiresAuthenticationRequestMatcher.matches(request)) {
            final String username = request.getHeader("username");
            final String password = request.getHeader("password");
            final EntityUser user = apiAuthenticationService.findByUsernameAndPassword(username, password);
            final ApiAuthenticationToken authRequest = new ApiAuthenticationToken(user.getUsername(), user.getPassword());
            authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
            Authentication authentication = this.authenticationManager.authenticate(authRequest);
            this.sessionStrategy.onAuthentication(authentication, request, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Authentication request failed: " + failed.toString(), failed);
            this.logger.debug("Updated SecurityContextHolder to contain null Authentication");
        }

        this.rememberMeServices.loginFail(request, response);
        response.sendError(401, failed.getMessage());
    }

}
