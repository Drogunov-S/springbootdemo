package ru.drogunov.springbootdemo.model;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum Role {
    ROLE_GUEST,
    ROLE_USER,
    ROLE_ADMIN;
    
    public static String[] getRoles() {
        String[] roles = new String[values().length];
                Arrays.stream(values())
                .map(Enum::toString)
                .toList()
                .toArray(roles);
        return roles;
    }
}
