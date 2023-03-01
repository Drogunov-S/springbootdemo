/*
package ru.drogunov.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.repositories.PeopleRepository;
import ru.drogunov.springbootdemo.security.PersonDetails;

import java.util.Optional;

*/
/**
 * Специальный сервис для SpringSecurity
 *//*

@Service
public class PersonDerailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;
    
    @Autowired
    public PersonDerailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    */
/**
     * Поиск человека в БД
     *//*

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByLogin(username);
        return new PersonDetails(person.orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found!");
        }));
    }
}
*/
