package ru.kpfu.itis.hauss.services;

import ru.kpfu.itis.hauss.models.Account;

public interface OAuthService {
    Account authenticate(String code);
}
