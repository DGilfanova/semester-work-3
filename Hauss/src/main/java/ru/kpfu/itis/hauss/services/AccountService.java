package ru.kpfu.itis.hauss.services;

import ru.kpfu.itis.hauss.dto.AccountDto;

public interface AccountService {
    AccountDto getAccountWithIdeas(Long authUserId);
}
