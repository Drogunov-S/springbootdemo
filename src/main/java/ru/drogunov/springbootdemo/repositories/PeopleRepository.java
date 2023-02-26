package ru.drogunov.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.model.Role;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
    
    Optional<Person> findByLogin(String login);
    
    @Transactional
    @Modifying
    @Query("update Person p set p.role = ?1 where p.id = ?2")
    int updateRoleById(Role role, Integer id);
}
