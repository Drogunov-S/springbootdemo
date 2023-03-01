package ru.drogunov.springbootdemo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springbootdemo.dto.person.PersonDTO;
import ru.drogunov.springbootdemo.exception.PersonNotCreatedException;
import ru.drogunov.springbootdemo.exception.PersonNotFoundException;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.services.PeopleService;
import ru.drogunov.springbootdemo.util.ErrorMassage;
import ru.drogunov.springbootdemo.util.PersonErrorResponse;
import ru.drogunov.springbootdemo.validators.PersonValidator;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    
    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }
    
    /**
     * Получение все людей для передачи на представление
     */
    @GetMapping
    public List<Person> index() {
        return peopleService.findAll();
    }
    
    /**
     * Get people from id, and return in view
     */
    @GetMapping("/{id}")
    public PersonDTO show(@PathVariable("id") Integer id) {
        return peopleService.findOne(id);
    }
    
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        return new ResponseEntity<>(
                new PersonErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorMassage errorMassage = new ErrorMassage(bindingResult.getFieldErrors());
            throw new PersonNotCreatedException(errorMassage.getMessage());
        }
        peopleService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        return new ResponseEntity<>(
                new PersonErrorResponse(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(person);
        return "redirect:" + person.getId();
    }
    
    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
