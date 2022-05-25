package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.hauss.dto.SignUpDto;
import ru.kpfu.itis.hauss.exceptions.DuplicateEmailException;
import ru.kpfu.itis.hauss.exceptions.UserConfirmException;
import ru.kpfu.itis.hauss.models.Account;
import ru.kpfu.itis.hauss.repositories.AccountsRepository;
import ru.kpfu.itis.hauss.services.RegistrationService;
import ru.kpfu.itis.hauss.utils.mail.EmailUtil;

import java.util.*;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.CONFIRM_MAIL_TEMPLATE_NAME;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final EmailUtil emailUtil;

    private final AccountsRepository accountsRepository;

    @Value("${mail.confirm.url}")
    private String mailConfirmUrl;

    @Transactional
    @Override
    public void signUp(SignUpDto signUpDto) {
        Account account = Account.builder()
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .phoneNumber(signUpDto.getPhoneNumber())
                .aboutMe(signUpDto.getAboutMe())
                .email(signUpDto.getEmail())
                .hashPassword(passwordEncoder.encode(signUpDto.getPassword()))
                .state(Account.State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .role(Account.Role.USER)
                .build();

        if (accountsRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new DuplicateEmailException("User with " + signUpDto.getEmail() + " email is already exist");
        }

        accountsRepository.save(account);

        Map<String, String> map = new HashMap<>();
        map.put("confirmLink",  mailConfirmUrl + account.getConfirmCode());
        map.put("userName", account.getFirstName() + " " + account.getLastName());

        emailUtil.sendMail(account.getEmail(), "Confirm mail for registration", CONFIRM_MAIL_TEMPLATE_NAME, map);
    }

    @Transactional
    @Override
    public void confirmUser(String confirmCode) {
        Account account = accountsRepository.findByConfirmCode(confirmCode)
                .orElseThrow(() -> new UserConfirmException("This confirm code doesn't exist"));

        account.setState(Account.State.CONFIRMED);
        accountsRepository.save(account);
    }
}

