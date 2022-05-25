package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hauss.dto.AccountDto;
import ru.kpfu.itis.hauss.exceptions.AccountNotFoundException;
import ru.kpfu.itis.hauss.models.Account;
import ru.kpfu.itis.hauss.models.Status;
import ru.kpfu.itis.hauss.repositories.AccountsRepository;
import ru.kpfu.itis.hauss.repositories.IdeasRepository;
import ru.kpfu.itis.hauss.services.AccountService;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountsRepository accountsRepository;
    private final IdeasRepository ideasRepository;

    @Override
    public AccountDto getAccountWithIdeas(Long authUserId) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return AccountDto.from(account, ideasRepository.findAllByStatusNotAndAccount_Id(Status.DELETED, authUserId));
    }


}

