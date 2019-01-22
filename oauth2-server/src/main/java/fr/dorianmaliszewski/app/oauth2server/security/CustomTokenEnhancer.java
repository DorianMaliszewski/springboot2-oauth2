package fr.dorianmaliszewski.app.oauth2server.security;

import fr.dorianmaliszewski.app.oauth2server.domains.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        User user = (User) oAuth2Authentication.getPrincipal();
        additionalInfo.put(
                "email", user.getEmail());
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(oAuth2AccessToken);
        customAccessToken.setAdditionalInformation(additionalInfo);
        return super.enhance(customAccessToken, oAuth2Authentication);
    }
}
