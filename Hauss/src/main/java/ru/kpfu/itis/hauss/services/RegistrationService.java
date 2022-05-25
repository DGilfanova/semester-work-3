package ru.kpfu.itis.hauss.services;

import ru.kpfu.itis.hauss.dto.SignUpDto;

public interface RegistrationService {

    void signUp(SignUpDto signUpDto);
    void confirmUser(String confirmCode);
}
