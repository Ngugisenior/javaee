package com.formula.racing.Formula.One.Racing.services;

import com.formula.racing.Formula.One.Racing.domain.Profile;
import com.formula.racing.Formula.One.Racing.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService extends AbstractService<Profile,Long>{


    @Autowired
    public ProfileService(AbstractRepository<Long, Profile> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public Optional<Profile> findByID(Long Id) {
        return super.findByID(Id);
    }

    @Override
    public List<Profile> findAll() {
        return super.findAll();
    }

    public Profile findByCarNumber(int car_number){
        for (Profile p: findAll()){
            if(p.getCar_number() == car_number){
                return p;
            }
        }
        return null;
    }

}
