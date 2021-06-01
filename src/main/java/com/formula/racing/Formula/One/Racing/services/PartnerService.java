package com.formula.racing.Formula.One.Racing.services;

import com.formula.racing.Formula.One.Racing.domain.Partner;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.domain.exception.PartnerNotFoundException;
import com.formula.racing.Formula.One.Racing.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class PartnerService extends AbstractService<Partner,Long> {



    @Autowired
    public PartnerService(AbstractRepository<Long, Partner> abstractRepository, TeamService teamService) {
        super(abstractRepository);
    }


    @Override
    public List<Partner> findAll() {
        return super.findAll();
    }

    public Partner findByName(String partner_name) throws PartnerNotFoundException{

        for(Partner p : findAll()){
            if(p.getName().equalsIgnoreCase(partner_name)){
                return p;
            }
        }
        return null;
    }

    @Override
    public Optional<Partner> findByID(Long Id)  {
        return Optional.of(super.findByID(Id)).orElseThrow(()-> new PartnerNotFoundException(Id));
    }


    @Override
    public Partner deleteById(Long Id) {
        Partner partner = findByID(Id).get();

        for (Team t : partner.getTeam()){
            t.getPartner().remove(partner);
        }

        return super.deleteById(Id);
    }

    public Partner addPartner(Partner partner){
        super.save(partner);
        return partner;
    }


    public Object persistPartners(Partner partner) {
        Partner p = new Partner();
        p.setName(partner.getName());


        for(Team t: p.getTeam()){
            p.addTeam(t);
        }


        super.save(p);
        return p;
    }
}
