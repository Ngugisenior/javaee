package com.formula.racing.Formula.One.Racing.controller;

import com.formula.racing.Formula.One.Racing.domain.Person;
import com.formula.racing.Formula.One.Racing.domain.Profile;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.services.PersonService;
import com.formula.racing.Formula.One.Racing.services.ProfileService;
import com.formula.racing.Formula.One.Racing.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final TeamService teamService;
    private final ProfileService profileService;

    @Autowired
    public PersonController(PersonService personService, TeamService teamService,ProfileService profileService) {
        this.personService = personService;
        this.teamService = teamService;
        this.profileService = profileService;
    }

    @RequestMapping("")
    String getAllPersons(Model model){
        model.addAttribute("person", this.personService.findAll());
        return "person/person";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPerson(Model model){
        List<Team> teamList = teamService.findAll();
        model.addAttribute("team", teamList);
        model.addAttribute("profile", new Profile());
        model.addAttribute("person", new Person());
        return "person/find/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updatePerson(Model model, Person person){
        Profile p = person.getProfile();
        profileService.save(p);
        model.addAttribute("person", person);
        personService.save(person);
        return "redirect:/person";
    }


    @RequestMapping(value = "/delete/{person_id}", method = RequestMethod.GET)
    String deletePersonById(@PathVariable("person_id") String person_id) throws Throwable {
        if(personService.findByID(Long.parseLong(person_id)).isPresent()) {
            personService.deletePersonById(Long.parseLong(person_id));
        }
        return "redirect:/person";
    }


    @RequestMapping(value = "/edit/{person_id}", method = RequestMethod.GET)
    public String editPersons(@PathVariable("person_id") String person_id, Model model) throws Throwable {
        if(personService.findByID(Long.parseLong(person_id)).isPresent()){

            model.addAttribute("teamz", teamService.findAll());
            model.addAttribute("person", (Person) personService.findByID(Long.parseLong(person_id)).get());

            return "person/find/edit";
        }
        else{
            return "redirect:/person";
        }
    }


    @RequestMapping(value = "/edit/{person_id}", method = RequestMethod.POST)
    public String updatePerson(@PathVariable("person_id") String person_id, Model model, Person person) throws Throwable {
        personService.editPerson(Long.parseLong(person_id),person);
        return "redirect:/person";
    }

    @RequestMapping(value = "/get_person/{person_id}", method = RequestMethod.GET)
    String getPersonById(@PathVariable("person_id") String person_id, Model model) throws Throwable {

        model.addAttribute("person", personService.findByID(Long.parseLong(person_id)).get());
        return "person/find/individual_person";
    }
}
