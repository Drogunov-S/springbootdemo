package ru.drogunov.springbootdemo.util;

import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorMassage {
    
    private String message;
    
    public ErrorMassage(List<FieldError> fieldErrors) {
        this.message = createMassage(fieldErrors);
    }
    
    private String createMassage(List<FieldError> fieldErrors) {
        this.message = fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return message;
    }
    
    public String getMessage() {
        return message;
    }
}
