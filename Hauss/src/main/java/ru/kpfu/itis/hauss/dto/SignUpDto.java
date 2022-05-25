package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.hauss.validation.annotations.FieldMatch;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldMatch(firstField = "password", secondField = "confirmPassword")
public class SignUpDto {

    @NotBlank(message = "Field shouldn't be empty")
    @Size(min = 2, max = 102, message = "Name should contain from 2 to 102 characters")
    private String firstName;

    @NotBlank(message = "Field shouldn't be empty")
    @Size(min = 2, max = 24, message = "Last name should contain from 1 to 24 characters")
    private String lastName;

    @NotBlank(message = "Field shouldn't be empty")
    @Pattern(regexp = "^(\\+7|7|8)+([0-9]){10}$", message = "Enter russian number like +79872000000")
    private String phoneNumber;

    @NotBlank(message = "Field shouldn't be empty")
    @Email(message = "It's not an email format")
    private String email;

    @NotBlank(message = "Field shouldn't be empty")
    private String password;

    @NotBlank(message = "Field shouldn't be empty")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[+!@#$%^&*])(?=.*[a-z])[0-9a-zA-Z!@#$%^&*+]{5,}",
            message = "Password should have numbers, symbols and special signs")
    private String confirmPassword;

    @NotBlank(message = "Field shouldn't be empty")
    @Size(max = 200, message = "Information about you should contain no more than 200 characters")
    private String aboutMe;
}
