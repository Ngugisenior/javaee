package com.formula.racing.Formula.One.Racing.services;

import com.formula.racing.Formula.One.Racing.domain.Person;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.domain.exception.PersonNotFoundException;
import com.formula.racing.Formula.One.Racing.repository.AbstractRepository;

import com.formula.racing.Formula.One.Racing.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService extends AbstractService<Person,Long> {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(AbstractRepository<Long, Person> abstractRepository,PersonRepository personRepository) {
        super(abstractRepository);
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Person> findByID(Long Id) {
        return Optional.of(super.findByID(Id).orElseThrow(() -> new PersonNotFoundException(Id)));
    }

    @Override
    public void save(Person person) {
        super.save(person);
    }

    public void editPerson(Long Id, Person person){

        Person p = (Person) findByID(Id).get();
        p.setFirst_name(person.getFirst_name());
        p.setLast_name(person.getLast_name());
        p.setProfile(person.getProfile());
        p.setTeam(person.getTeam());
        save(p);
        //return p;
    }

    public void deletePersonById(Long Id) {
        if(findByID(Id).isPresent()){
            Person p = findByID(Id).get();
            Team t = p.getTeam();
            t.getPersonSet().remove(p);
            super.deleteById(Id);
        }

    }
}
