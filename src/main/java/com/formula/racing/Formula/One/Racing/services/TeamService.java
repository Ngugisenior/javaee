package com.formula.racing.Formula.One.Racing.services;

import com.formula.racing.Formula.One.Racing.domain.Partner;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.domain.exception.TeamNotFoundException;
import com.formula.racing.Formula.One.Racing.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService extends AbstractService<Team,Long>{


    @Autowired
    public TeamService(AbstractRepository<Long, Team> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public List<Team> findAll() {
        return super.findAll();
    }

    public Team findTeamIfExists(String team_name) throws TeamNotFoundException{
        for (Team t: findAll()){
            if(t.getTeam_name().equalsIgnoreCase(team_name)){
                return t;
            }
        }
        return null;
    }

    @Override
    public Team deleteById(Long Id) {
        Team team = findByID(Id).get();

        for (Partner p : team.getPartner()){
            p.getTeam().remove(team);
        }
        super.deleteById(Id);
        return null;
    }

    @Override
    public Optional<Team> findByID(Long Id){
        return  Optional.of(super.findByID(Id).orElseThrow(() -> new TeamNotFoundException(Id)));
    }

    public Team editTeam(Long Id, Team t) throws TeamNotFoundException{
        Team teamToEdit = findByID(Id).get();
        teamToEdit.setTeam_name(t.getTeam_name());
        teamToEdit.setPower_unit(t.getPower_unit());
        teamToEdit.setWorld_championships(t.getWorld_championships());
        for(Partner p : t.getPartner()){
            teamToEdit.addPartner(p);
        }

        return teamToEdit;
    }

    public void persistPartners(List <Team> team) {
        for(Team t : team){
            Team ts = findByID(t.getId()).get();
             for(Partner p: ts.getPartner()){
                 ts.addPartner(p);
             }
             super.save(ts);
        }


    }

    public void saveTeamPartners(Long team_id, Team team) {
        if( team!= null || team.getTeam_name() != null || !team.getTeam_name().isEmpty()){
            Team t = findByID(team_id).get();
            t.setTeam_name(team.getTeam_name());
            t.setWorld_championships(team.getWorld_championships());
            t.setPower_unit(team.getPower_unit());
            for(Partner p : team.getPartner()){
                t.addPartner(p);
            }
            super.save(t);
            //return "redirect:/team";
        }
    }
}
