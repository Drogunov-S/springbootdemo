package ru.drogunov.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.services.PeopleService;

@Controller
@RequestMapping("/admin")
public class AdminsController {
    
    private final PeopleService peopleService;
    
    @Autowired
    public AdminsController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
    
    @GetMapping
    public String index(Model model,
                        @ModelAttribute("person") Person person) {
        model.addAttribute("people", peopleService.findAll());
        return "admin/admin";
    }
    
    @PatchMapping("/add")
    public String add(@ModelAttribute("person") Person person,
                      @RequestParam("role") String role) {
        peopleService.update(person, role);
        return "redirect: /admin";
    }
}
