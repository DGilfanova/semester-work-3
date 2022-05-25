package ru.kpfu.itis.hauss.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kpfu.itis.hauss.dto.ApiResponse;
import ru.kpfu.itis.hauss.dto.ErrorDto;
import ru.kpfu.itis.hauss.exceptions.GeneralNotFoundException;
import ru.kpfu.itis.hauss.exceptions.ReviewNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlersController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlersController.class);

    @ExceptionHandler(GeneralNotFoundException.class)
    public String handleNotFoundException(GeneralNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "custom_error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleAllException(Model model, Exception e) {
        logger.error("Server error [" + e.getMessage() + "]");

        model.addAttribute("error", e.getMessage());
        return "custom_error";
    }

    //FOR REST

    @ResponseBody
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleValidationException(MethodArgumentNotValidException exception) {
        List<ErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ErrorDto errorDto = ErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setObject(fieldName);
            } else if (error instanceof ObjectError) {
                String objectName = error.getObjectName();
                errorDto.setObject(objectName);
            }
            errors.add(errorDto);
        });
        return ApiResponse.builder().success(false).errors(errors).build();
    }

    @ResponseBody
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException.class)
    public ApiResponse handleReviewNotFoundException(ReviewNotFoundException e) {
        return ApiResponse.builder()
                .success(false)
                .errors(Collections.singletonList(
                        ErrorDto.builder()
                                .object(e.getClass().getCanonicalName())
                                .message(e.getMessage())
                                .build()))
                .build();
    }
}

