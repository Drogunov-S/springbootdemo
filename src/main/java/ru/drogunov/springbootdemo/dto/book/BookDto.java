package ru.drogunov.springbootdemo.dto.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import ru.drogunov.springbootdemo.model.Person;

import java.time.Year;
import java.util.Date;

public class BookDto {
    private Integer id;
    @JsonBackReference
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

}
