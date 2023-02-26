package ru.drogunov.springbootdemo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.services.PeopleService;
import ru.drogunov.springbootdemo.validators.PersonValidator;

@Controller
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
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/people";
    }
    
    /**
     * Get people from id, and return in view
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }
    
    @GetMapping("/new")
//    public String newPerson(@ModelAttribute("person") Person person) {
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") Integer id) {
        Person show = peopleService.findOne(id);
        model.addAttribute("person", show);
        return "people/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(person);
        return "redirect:"+ person.getId();
    }
    
    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
