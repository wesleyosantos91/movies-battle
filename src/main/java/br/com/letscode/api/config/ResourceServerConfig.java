package br.com.letscode.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final JwtTokenStore tokenStore;

    private final Environment env;

    private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**", "/v3/api-docs", "/" };

    private static final String[] PLAYER_OR_ADMIN = { "/movies/**", "/quizs/**", "/rankings/**" };

    private static final String[] ADMIN = { "/users/**" };

    public ResourceServerConfig(JwtTokenStore tokenStore, Environment env) {
        this.tokenStore = tokenStore;
        this.env = env;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //H2
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, PLAYER_OR_ADMIN).permitAll()
                .antMatchers(PLAYER_OR_ADMIN).hasAnyRole("PLAYER", "ADMIN")
                .antMatchers(ADMIN).hasAnyRole("ADMIN")
                .anyRequest().authenticated();
    }
}
