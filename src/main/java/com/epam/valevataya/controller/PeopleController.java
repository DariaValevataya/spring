package com.epam.valevataya.controller;

import com.epam.valevataya.dao.PersonDao;
import com.epam.valevataya.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
  private final PersonDao personDao;

  public PeopleController(PersonDao personDao) {
    this.personDao = personDao;
  }

  @GetMapping()
  public String getAllPeople(Model model) {
    model.addAttribute("people", personDao.getPeople());
    return "people/people";
  }

  @GetMapping("/{id}")
  public String getPerson(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personDao.getPerson(id));
    return "people/person";
  }

  @GetMapping("/new")
  public String newPerson(Model model) {
    model.addAttribute("person", new Person());
    return "people/new";
  }

  @PostMapping()
  public String createPerson(@ModelAttribute("person") Person person) {
    personDao.save(person);
    return "redirect:/people";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable("id") int id){
    model.addAttribute("person", personDao.getPerson(id));
    return "people/edit";
  }

  @PatchMapping("/{id}")
  public String update(@ModelAttribute("person")  Person person, @PathVariable("id") int id){
    personDao.update(id, person);
    return "redirect:/people";
  }
  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id){
    personDao.delete(id);
    return "redirect:/people";
  }
}
