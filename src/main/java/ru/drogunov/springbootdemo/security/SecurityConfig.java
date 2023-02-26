package ru.drogunov.springbootdemo.security;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    
    private final AuthProviderImpl authProvider;
    
    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }
    
//    /**
//     * Настройка аутентификации
//     **
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }
}*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final AuthProviderImpl authProvider;
    
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, AuthProviderImpl authProvider) {
        this.userDetailsService = userDetailsService;
        this.authProvider = authProvider;
    }
    
    /**
     * Настройка SpringSecurity и авторизации
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                                authorize
                                        // Выполняют одинаковые действие со строчной ниже, но есть допуск к JS и прочему
//                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/css/**", "/images/**").permitAll()
                                        .requestMatchers("/auth/login", "/error", "/auth/registration").permitAll()
                                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/process_login")
                                .defaultSuccessUrl("/", true)
                                .failureForwardUrl("/auth/login?error")
                                .failureUrl("/auth/login?error")
                );
        return http.build();
    }
    
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
        ;
    }
}
