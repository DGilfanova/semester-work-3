package ru.kpfu.itis.hauss.utils.api.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.hauss.dto.VkOAuthToken;
import ru.kpfu.itis.hauss.dto.VkAccountAuthResponse;
import ru.kpfu.itis.hauss.dto.VkAccountDto;
import ru.kpfu.itis.hauss.exceptions.VkOAuthException;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class VkOAuthUtilsImpl implements VkOAuthUtils {

    private final VkOAuthUriGenerator uriGenerator;
    private final ObjectMapper objectMapper;
    private final OkHttpClient okHttpClient;

    private static final Logger logger = LoggerFactory.getLogger(VkOAuthUtilsImpl.class);

    @Override
    public VkOAuthToken authUser(String code) throws VkOAuthException {
        Request request = new Request.Builder()
                .url(uriGenerator.getAccessTokenUri(code))
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();

            logger.info("Successfully get vk oAuth token");

            return objectMapper.readValue(response.body().bytes(), VkOAuthToken.class);
        } catch (IOException e) {
            logger.error("Failed to get vk oAuth token. Problem with vk server.");

            throw new VkOAuthException("Problem with vk server. Please try later", e);
        }
    }

    @Override
    public VkAccountDto getUserInfo(VkOAuthToken authToken) throws VkOAuthException {
        Request request = new Request.Builder()
                .url(uriGenerator.getUserInfoUri(authToken))
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();

            logger.info("Successfully get vk user information for " + authToken.getEmail());

            VkAccountAuthResponse userAuthResponse = objectMapper.readValue(response.body().bytes(), VkAccountAuthResponse.class);

            if (userAuthResponse == null) {
                logger.error("Authorization failed for " + authToken.getEmail());

                throw new VkOAuthException("Authorization failed");
            }
            return userAuthResponse.getResponse().get(0);
        } catch (IOException e) {
            logger.error("Failed to get " + authToken.getEmail() + " information");

            throw new VkOAuthException("Problem with vk server. Please try later", e);
        }
    }
}
