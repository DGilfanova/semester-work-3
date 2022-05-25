package ru.kpfu.itis.hauss.utils.api.oauth;

import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.hauss.dto.VkOAuthToken;

@Component
public class VkOAuthUriGenerator {

    @Value("${oauth.vk.version}")
    private String vkApiVersion;

    @Value("${oauth.vk.client_id}")
    private String vkClientId;

    @Value("${oauth.vk.client_secure_key}")
    private String vkClientSecureKey;

    @Value("${oauth.vk.redirect_uri}")
    private String vkRedirectUrl;

    @Value("${oauth.vk.response_type}")
    private String vkResponseType;

    @Value("${oauth.vk.api.auth_url}")
    private String vkApiAuthUrl;

    @Value("${oauth.vk.api.get_user_url}")
    private String vkApiGetUserUrl;

    @Value("${oauth.vk.api.get_token_url}")
    private String vkApiGetTokenUrl;

    public String getAuthCodeUri() {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(vkApiAuthUrl).newBuilder();
        urlBuilder.addQueryParameter("client_id", vkClientId);
        urlBuilder.addEncodedQueryParameter("redirect_uri", vkRedirectUrl);
        urlBuilder.addQueryParameter("response_type", vkResponseType);
        urlBuilder.addQueryParameter("scope", "email");
        urlBuilder.addQueryParameter("v", vkApiVersion);

        return urlBuilder.build().toString();
    }

    public String getAccessTokenUri(String code) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(vkApiGetTokenUrl).newBuilder();
        urlBuilder.addQueryParameter("client_id", vkClientId);
        urlBuilder.addQueryParameter("client_secret", vkClientSecureKey);
        urlBuilder.addQueryParameter("code", code);
        urlBuilder.addEncodedQueryParameter("redirect_uri", vkRedirectUrl);

        return urlBuilder.build().toString();
    }

    public String getUserInfoUri(VkOAuthToken authToken) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(vkApiGetUserUrl).newBuilder();
        urlBuilder.addQueryParameter("v", vkApiVersion);
        urlBuilder.addQueryParameter("uids", authToken.getUserId().toString());
        urlBuilder.addQueryParameter("access_token", authToken.getAccessToken());

        return urlBuilder.build().toString();
    }
}
