package ru.drogunov.springbootdemo.util;

public class PersonErrorResponse {
    private String massage;
    private Long timestamp;
    
    public PersonErrorResponse(String massage) {
        this.massage = massage;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getMassage() {
        return massage;
    }
    
    public void setMassage(String massage) {
        this.massage = massage;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
