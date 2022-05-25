package ru.kpfu.itis.hauss.security.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.hauss.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        Map<Account.Role, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put(Account.Role.USER, "/profile");
        roleTargetUrlMap.put(Account.Role.ADMIN, "/welcome");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (roleTargetUrlMap.containsKey(Account.Role.valueOf(authorityName))) {
                return roleTargetUrlMap.get(Account.Role.valueOf(authorityName));
            }
        }

        throw new IllegalStateException();
    }
}
