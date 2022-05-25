package ru.kpfu.itis.hauss.security.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.hauss.security.details.AccountUserDetails;

@Component
public class VkOAuthAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(VkOAuthAuthenticationProvider.class);

    public VkOAuthAuthenticationProvider(@Qualifier(value = "vkOAuthAccountDetailsService")
                                                 UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        VkOAuthAuthentication vkOAuthAuthentication = (VkOAuthAuthentication) authentication;
        vkOAuthAuthentication.setUserDetails(
                userDetailsService.loadUserByUsername(
                        ((VkOAuthAuthentication) authentication).getCode()));
        vkOAuthAuthentication.setAuthenticated(true);

        logger.info("Successful authentication for " +
                ((AccountUserDetails) vkOAuthAuthentication.getUserDetails()).getAccount().getEmail());

        return vkOAuthAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(VkOAuthAuthentication.class);
    }
}
