package ru.ilnik.garage.security.oauth2;

import ru.ilnik.garage.model.oauth.GoogleOAuth2UserInfo;
import ru.ilnik.garage.model.oauth.OAuth2UserInfo;
import ru.ilnik.garage.model.oauth.VkOAuth2UserInfo;
import ru.ilnik.garage.model.oauth.AuthProvider;
import ru.ilnik.garage.util.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.name())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.VK.name())) {
            return new VkOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
