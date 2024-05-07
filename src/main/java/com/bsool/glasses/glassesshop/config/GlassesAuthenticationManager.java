package com.bsool.glasses.glassesshop.config;

import com.bsool.glasses.glassesshop.config.security.ApiAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;

import java.util.Collections;

@Configuration
public class GlassesAuthenticationManager extends ProviderManager {

    public GlassesAuthenticationManager(ApiAuthenticationProvider apiAuthenticationProvider) {
        super(Collections.singletonList(apiAuthenticationProvider));

    }
}
