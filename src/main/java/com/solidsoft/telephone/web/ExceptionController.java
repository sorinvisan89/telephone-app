package com.solidsoft.telephone.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResponseStatusException.class)
    public String handleExceptions(final ResponseStatusException exception, final Model model){
        model.addAttribute("httpStatus", exception.getStatus().value());
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }
}
