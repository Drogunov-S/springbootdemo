package ru.drogunov.springbootdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @NotEmpty(message = "Name not by null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Column(name = "year_birth")
    @Min(value = 1850, message = "Min year age 1850 ")
    private Integer yearOfBrith;
    @Email
    private String email;
    @Pattern(
            regexp = "([A-Z]+|[А-Я]+)(\\w+|[а-я]+), ([A-Z]+|[А-Я]+)(\\w+|[а-я]+), \\d{6}, .+",
            message = "Формат: \"Страна, Город, 123456, \""
    )
    private String address;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private List<Book> books;
    
    public Person() {
    }
    
    public Person(Integer id, String name, Integer yearBrith, String email, String address, Role role, List<Book> books) {
        this.id = id;
        this.name = name;
        this.yearOfBrith = yearBrith;
        this.email = email;
        this.address = address;
        this.role = role;
        this.books = books;
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
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
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
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
