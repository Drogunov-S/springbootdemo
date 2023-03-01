package ru.drogunov.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drogunov.springbootdemo.exception.PersonNotFoundException;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.model.Role;
import ru.drogunov.springbootdemo.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
    
    public Person findOne(Integer id) {
        return peopleRepository.findById(id).orElseThrow(() -> {
            throw new PersonNotFoundException("Person not found.");
        });
    }
    
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
    
    @Transactional
    public void update(Person updatedPerson) {
        peopleRepository.save(updatedPerson);
    }
    
    @Transactional
    public void update(Person person, String role) {
        peopleRepository.updateRoleById(Role.valueOf(role), person.getId());
    }
    
    @Transactional
    public void delete(Integer id) {
        peopleRepository.deleteById(id);
    }
    
    /*Todo for validators*/
    public Optional<Person> findByLogin(String login) {
        return peopleRepository.findByLogin(login);
    }
    
    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }
}
