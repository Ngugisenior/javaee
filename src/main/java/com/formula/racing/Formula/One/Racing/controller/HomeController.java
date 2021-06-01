package com.formula.racing.Formula.One.Racing.controller;


import com.formula.racing.Formula.One.Racing.domain.Partner;
import com.formula.racing.Formula.One.Racing.domain.Progress;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.services.PartnerService;
import com.formula.racing.Formula.One.Racing.services.ProgressService;

import com.formula.racing.Formula.One.Racing.services.TeamService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Controller
@RequestMapping()
public class HomeController {

    private final PartnerService partnerService;
    private final TeamService teamService;
    private final ProgressService progressService;

    public HomeController(PartnerService partnerService, TeamService teamService, ProgressService progressService) {
        this.partnerService = partnerService;
        this.teamService = teamService;
        this.progressService = progressService;
    }

    @GetMapping
    String home(Model model){
        model.addAttribute("progress", progressService.findAll());

        return "index";
    }

    @GetMapping("partner/add")
    String addPartner(Model model){

        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("partner", new Partner());

        return "partners/add";
    }

    @RequestMapping(value = "partner/add",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String persistPartner(Partner partner ){
        for(Team t: partner.getTeam()){
            t.addPartner(partner);
        }
        partnerService.save(partner);

        return "redirect:/partner";
    }


    @RequestMapping(value = "partner/edit/{partner_id}", method = RequestMethod.GET)
    String editPartner(@PathVariable("partner_id") String partner_id, Model model){
        Partner p = partnerService.findByID(Long.parseLong(partner_id)).get();

        for (Team t : p.getTeam()){
            t.getPartner().remove(this);
        }

        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("partner", p);
        return "partners/edit";
    }

    @RequestMapping(value = "partner/edit/{partner_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String postPartner(@PathVariable("partner_id") String partner_id, Model model, Partner partner){
        Partner p = partnerService.findByID(Long.parseLong(partner_id)).get();
        p.setName(partner.getName());

        for(Team t : partner.getTeam()){
            p.addTeam(t);
        }

        partnerService.save(p);

        return "redirect:/partner";
    }




}
