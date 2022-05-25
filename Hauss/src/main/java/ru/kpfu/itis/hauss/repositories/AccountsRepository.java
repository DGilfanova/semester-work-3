package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.hauss.models.Account;

import java.util.Optional;

public interface AccountsRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByConfirmCode(String confirmCode);
}
