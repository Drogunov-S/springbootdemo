package ru.drogunov.springbootdemo.dto.person;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import ru.drogunov.springbootdemo.model.Book;

import java.util.List;

public class PersonDTO {
    private String login;
    private String password;
    @NotEmpty(message = "Name not by null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Min(value = 1850, message = "Min year age 1850")
    private Integer yearOfBrith;
    @Email
    private String email;
    @Pattern(
            regexp = "([A-Z]+|[А-Я]+)(\\w+|[а-я]+), ([A-Z]+|[А-Я]+)(\\w+|[а-я]+), \\d{6}, .+",
            message = "Формат: \"Страна, Город, 123456, \""
    )
    private String address;
    //    @Enumerated(EnumType.STRING)
//  private Role role;
    
    public PersonDTO() {
    }
    
    public PersonDTO(String login, String password, String name, Integer yearOfBrith, String email, String address) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.yearOfBrith = yearOfBrith;
        this.email = email;
        this.address = address;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getYearOfBrith() {
        return yearOfBrith;
    }
    
    public void setYearOfBrith(Integer yearOfBrith) {
        this.yearOfBrith = yearOfBrith;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}
