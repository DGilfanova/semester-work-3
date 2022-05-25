package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hauss.dto.VkAccountDto;
import ru.kpfu.itis.hauss.dto.VkOAuthToken;
import ru.kpfu.itis.hauss.exceptions.VkOAuthException;
import ru.kpfu.itis.hauss.models.Account;
import ru.kpfu.itis.hauss.repositories.AccountsRepository;
import ru.kpfu.itis.hauss.services.OAuthService;
import ru.kpfu.itis.hauss.utils.api.oauth.VkOAuthUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VkOAuthServiceImpl implements OAuthService {

    private final VkOAuthUtils vkOAuthUtils;
    private final AccountsRepository accountsRepository;

    private static final Logger logger = LoggerFactory.getLogger(VkOAuthServiceImpl.class);

    @Override
    public Account authenticate(String code) {

        //1:get access token
        VkOAuthToken token = vkOAuthUtils.authUser(code);

        if (token.getEmail() == null) {
            logger.error("Failed authentication because vk user don't have an email");

            throw new VkOAuthException("Email required");
        }

        //2.1: return existing user
        Optional<Account> optionalAccount = accountsRepository.findByEmail(token.getEmail());
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }

        //2.2:get account information and create new account
        VkAccountDto accountDto = vkOAuthUtils.getUserInfo(token);
        Account account = Account.builder()
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .phoneNumber(accountDto.getPhoneNumber())
                .aboutMe(accountDto.getAboutMe())
                .email(token.getEmail())
                .state(Account.State.CONFIRMED)
                .role(Account.Role.USER)
                .build();

        return accountsRepository.save(account);
     }
}
