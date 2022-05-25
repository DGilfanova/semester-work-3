package ru.kpfu.itis.hauss.security.oauth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class VkOAuthUserAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code != null) {
            SecurityContextHolder.getContext().setAuthentication(new VkOAuthAuthentication(code));
        }

        filterChain.doFilter(request, response);
    }
}
