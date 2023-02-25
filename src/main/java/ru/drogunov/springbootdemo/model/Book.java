package ru.drogunov.springbootdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;
import java.util.Date;

import static java.util.Objects.isNull;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @Size(min = 3, max = 100)
    private String title;
    @Size(min = 3, max = 100)
    private String author;
    @Column(name = "year", columnDefinition = "integer")
    @DateTimeFormat(pattern = "YYYY")
    private Year yearManufactured;
    
    @Column(name = "taken_at")
    private Date takenAt;
    
    @Transient
    private Boolean expired;
    public Book() {
    }
    
    public Book(Integer id, Person person, String title, String author, Year yearManufactured) {
        this.id = id;
        this.person = person;
        this.title = title;
        this.author = author;
        this.yearManufactured = yearManufactured;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Year getYearManufactured() {
        return yearManufactured;
    }
    
    public void setYearManufactured(Year yearManufactured) {
        this.yearManufactured = yearManufactured;
    }
    
    public Person getPerson() {
        return person;
    }
    
    public Date getTakenAt() {
        return takenAt;
    }
    
    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }
    
    public Boolean getExpired() {
        return expired;
    }
    
    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
    
    public boolean isFree() {
        return isNull(person);
    }
    
}
