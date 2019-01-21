package fr.dorianmaliszewski.app.oauth2resource.config;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${app.oauth.url}")
    private String OAUTH_URL;
    @Value("${app.oauth.client-id}")
    private String OAUTH_CLIENT_ID;
    @Value("${app.oauth.client-secret}")
    private String OAUTH_CLIENT_SECRET;
    @Value("${app.oauth.resource-id}")
    private String RESOURCE_ID;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().httpBasic().disable().authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED));
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices()).resourceId(RESOURCE_ID);
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl(
                OAUTH_URL + "/oauth/check_token"
        );
        tokenServices.setClientId(OAUTH_CLIENT_ID);
        tokenServices.setClientSecret(OAUTH_CLIENT_SECRET);
        return tokenServices;
    }

}
