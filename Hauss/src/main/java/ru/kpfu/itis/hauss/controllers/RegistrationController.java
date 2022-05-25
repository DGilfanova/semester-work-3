package ru.kpfu.itis.hauss.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.hauss.dto.SignInDto;
import ru.kpfu.itis.hauss.dto.SignUpDto;
import ru.kpfu.itis.hauss.exceptions.DuplicateEmailException;
import ru.kpfu.itis.hauss.exceptions.UserConfirmException;
import ru.kpfu.itis.hauss.services.RegistrationService;
import ru.kpfu.itis.hauss.utils.api.oauth.VkOAuthUriGenerator;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    private final VkOAuthUriGenerator uriGenerator;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "signup";
    }

    @GetMapping("/signin")
    public String getSignInPage(Model model) {
        model.addAttribute("signInDto", new SignInDto());
        model.addAttribute("oAuthUri", uriGenerator.getAuthCodeUri());
        return "signin";
    }

    @GetMapping("/vk-auth")
    public String getVkAuthPage() {
        return "redirect:/profile";
    }

    @GetMapping("/")
    public String getRootPage() {
        return "welcome";
    }

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome";
    }

    @GetMapping("/confirm")
    public String getConfirmPage() {
        return "confirm";
    }

    @GetMapping("/confirm/{confirmCode}")
    public String confirmUser(@PathVariable("confirmCode") String confirmCode) {
        try {
            registrationService.confirmUser(confirmCode);
            return "redirect:/confirm?success";
        } catch (UserConfirmException e) {
            return "redirect:/confirm?error";
        }
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute("signUpDto") SignUpDto signUpDto,
                         BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("signUpDto", signUpDto);
            return "signup";
        } else {
            try {
                registrationService.signUp(signUpDto);
                return "redirect:/signin?success";
            } catch (DuplicateEmailException e) {
                logger.warn("Duplicate email: " + e.getMessage());

                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/signup";
            }
        }
    }
}

