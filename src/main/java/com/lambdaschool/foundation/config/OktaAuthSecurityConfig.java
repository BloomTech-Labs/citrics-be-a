package com.lambdaschool.foundation.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OktaAuthSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    // see https://www.devglan.com/spring-security/spring-boot-jwt-auth
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception
    {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .antMatchers("/",
                "/h2-console/**",
                "/swagger-resources/**",
                "/swagger-resource/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                //this /cities  isnt doing anything for some reason. I added /cities to two places because of a reddit thread I found that has a similar issues
                    // not sure why it works, but it does.
                "/webjars/**","/cities/**")
            .permitAll()
            .antMatchers(HttpMethod.POST,
                "/users/**")
            .permitAll()

            .antMatchers(HttpMethod.DELETE,
                "/users/**")
            .permitAll()
            .antMatchers(HttpMethod.PUT,
                "/users/**")
            .permitAll()

            .antMatchers("/users/**",
                "/oauth/revoke-token",
                "/logout","/cities/**")
            .permitAll()
            .antMatchers("/roles/**","/cities/**")
            .permitAll()
            .and()
            .exceptionHandling()
            .and()
            .oauth2ResourceServer()
            .jwt();

        // process CORS annotations
        // http.cors();

        // disable the creation and use of Cross Site Request Forgery Tokens.
        // These tokens require coordination with the front end client that is beyond the scope of this class.
        // See https://www.yawintutor.com/how-to-enable-and-disable-csrf/ for more information
        http
            .csrf()
            .disable();

        // force a non-empty response body for 401's to make the response more browser friendly
        Okta.configureResourceServer401ResponseBody(http);

        // h2 console
        http.headers()
            .frameOptions()
            .disable();
    }

}