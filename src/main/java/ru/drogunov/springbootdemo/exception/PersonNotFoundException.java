package ru.drogunov.springbootdemo.exception;

public class PersonNotFoundException extends RuntimeException {
    private String message;
    
    public PersonNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    
    public PersonNotFoundException(){}
    
    @Override
    public String getMessage() {
        return message;
    }
}
