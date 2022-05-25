package ru.kpfu.itis.hauss.utils.api.oauth;

import ru.kpfu.itis.hauss.dto.VkOAuthToken;
import ru.kpfu.itis.hauss.dto.VkAccountDto;
import ru.kpfu.itis.hauss.exceptions.VkOAuthException;

public interface VkOAuthUtils {
    VkOAuthToken authUser(String code) throws VkOAuthException;
    VkAccountDto getUserInfo(VkOAuthToken authToken) throws VkOAuthException;

}
