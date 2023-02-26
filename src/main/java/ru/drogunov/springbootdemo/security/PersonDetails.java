package ru.drogunov.springbootdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.drogunov.springbootdemo.model.Person;

import java.util.Collection;

public class PersonDetails implements UserDetails {
    private final Person person;
    
    public PersonDetails(Person person) {
        this.person = person;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    
    @Override
    public String getPassword() {
        return this.person.getPassword();
    }
    
    @Override
    public String getUsername() {
        return this.person.getLogin();
    }
    
    /**
     * Аккаунт не просрочен?
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
     * Аккаунт заблокирован?
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
     * Пароль просрочен?
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
     * Аккаунт активен?
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    /**
     * Для доступа к полям аутентифицированного пользователя, скорее всего нужно делать immutable
     */
    public Person getPerson() {
        return person;
    }
}
