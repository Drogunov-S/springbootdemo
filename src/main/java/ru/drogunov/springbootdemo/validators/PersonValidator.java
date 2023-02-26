package ru.drogunov.springbootdemo.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.services.PeopleService;

import java.util.Objects;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;
    
    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> personFromDb = peopleService.findByEmail(person.getEmail());
        if (checkByEmail(person)) {
            errors.rejectValue("email", "", "This email is already taken");
        } else if (checkByLogin(person)) {
            errors.rejectValue("username", "", "This login is busy");
        }
    }
    
    private boolean checkByEmail(Person target) {
        Optional<Person> personFromDb = peopleService.findByEmail(target.getEmail());
        return predicateByIsPresentAndById(personFromDb, target);
    }
    
    private boolean checkByLogin(Person target) {
        Optional<Person> personFromDb = peopleService.findByLogin(target.getLogin());
        return predicateByIsPresentAndById(personFromDb, target);
    }
    
    private boolean predicateByIsPresentAndById(Optional<Person> personFromDb, Person target) {
        return personFromDb.isPresent()
                && !Objects.equals(target.getId(), personFromDb.get().getId());
    }
}








