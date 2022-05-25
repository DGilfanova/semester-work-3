package ru.kpfu.itis.hauss.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hauss.services.OAuthService;

@RequiredArgsConstructor
@Service(value = "vkOAuthAccountDetailsService")
public class VkOAuthAccountUserDetailsService implements UserDetailsService {

    private final OAuthService oAuthService;

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
         return new AccountUserDetails(oAuthService.authenticate(code));
    }
}
