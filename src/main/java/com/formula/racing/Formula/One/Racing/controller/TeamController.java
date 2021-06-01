package com.formula.racing.Formula.One.Racing.controller;

import com.formula.racing.Formula.One.Racing.domain.Partner;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.services.PartnerService;
import com.formula.racing.Formula.One.Racing.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;
    private final PartnerService partnerService;

    @Autowired
    public TeamController(TeamService teamService, PartnerService partnerService) {
        this.teamService = teamService;
        this.partnerService= partnerService;
    }

    @GetMapping
    String getAllTeams(Model model){
        model.addAttribute("teams", teamService.findAll());
        return "teams/teams";
    }

    @GetMapping(value = "/peopleList/{team_id}")
    String showPeopleList(@PathVariable("team_id") String team_id, Model model) throws Throwable {
        if(teamService.findByID(Long.parseLong(team_id)).isPresent()){
          model.addAttribute("teams", teamService.findByID(Long.parseLong(team_id)).get());
            return "teams/forms/people_list_team";
        }
        else{
            return "redirect:/team";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String addTeam(Model model){
        model.addAttribute("partners", partnerService.findAll());
        model.addAttribute("teams", new Team());
        return "teams/forms/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String persistTeam(Model model, Team team){
        model.addAttribute("teams", team);
        if(team != null){
            for(Partner p : team.getPartner()){
                p.addTeam(team);
            }
            teamService.save(team);
            return "redirect:/team";
        }
        return "teams/forms/add";
    }

    @RequestMapping(value = "/edit/{team_id}", method = RequestMethod.GET)
    String editTeam(@PathVariable("team_id") String team_id,Model model) throws Throwable {

        model.addAttribute("partners", partnerService.findAll());
        model.addAttribute("teams",teamService.findByID(Long.parseLong(team_id)).get());
        return "teams/update/edit";
    }

    @RequestMapping(value = "/edit/{team_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String updateTeam(@PathVariable("team_id") String team_id, Model model, Team team) throws Throwable {
        model.addAttribute("teams", team);
        teamService.saveTeamPartners(Long.parseLong(team_id), team);
        return "redirect:/team";
    }

    @RequestMapping(value = "/delete/{team_id}", method = RequestMethod.GET)
    String deleteById(@PathVariable("team_id") String team_id, Model model) throws Throwable {
        if(teamService.findByID(Long.parseLong(team_id)).isPresent()){
            teamService.deleteById(Long.parseLong(team_id));
        }
        return "redirect:/team";
    }

}
