package com.formula.racing.Formula.One.Racing.controller;

import com.formula.racing.Formula.One.Racing.domain.Partner;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.services.PartnerService;
import com.formula.racing.Formula.One.Racing.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerService partnerService;
    private final TeamService teamService;

    @Autowired
    public PartnerController(PartnerService partnerService, TeamService teamService) {
        this.partnerService = partnerService;
        this.teamService = teamService;
    }


    @GetMapping
    String getAllPartners(Model model){
        model.addAttribute("partners", partnerService.findAll());
        return "partners/partner";
    }

    @RequestMapping(value = "/get_partner/{partner_id}", method = RequestMethod.GET)
    String getPartnerById(@PathVariable("partner_id") String partner_id, Model model) throws Throwable {
        if(partnerService.findByID(Long.parseLong(partner_id)).isPresent()){
            model.addAttribute("partner", partnerService.findByID(Long.parseLong(partner_id)).get());
            return "partners/individual_partner";
        }
        return "redirect:/partner";
    }



    @GetMapping("/delete/{partner_id}")
    String deletePartner(@PathVariable("partner_id") String Id, Model model){
        partnerService.deleteById(Long.parseLong(Id));
        model.addAttribute("partner", partnerService.findAll());
        return "redirect:/partner";
    }


}
