package ru.kpfu.itis.hauss.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kpfu.itis.hauss.security.oauth.VkOAuthUserAuthenticationFilter;

@Order(1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuthWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private VkOAuthUserAuthenticationFilter authenticationFilter;

    @Autowired
    private AuthenticationProvider vkOAuthAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.antMatcher("/vk-auth/**")
                .authorizeRequests()
                .antMatchers("/vk-auth").authenticated()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/signin?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .formLogin().disable()
                .httpBasic().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(vkOAuthAuthenticationProvider);
    }
}
