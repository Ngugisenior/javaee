package com.formula.racing.Formula.One.Racing.services;


import com.formula.racing.Formula.One.Racing.domain.AbstractEntity;
import com.formula.racing.Formula.One.Racing.domain.Team;
import com.formula.racing.Formula.One.Racing.repository.AbstractRepository;

import java.io.Serializable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class AbstractService<T extends AbstractEntity<ID>, ID extends Serializable> {

    private  final AbstractRepository<ID, T> abstractRepository;


    public AbstractService(AbstractRepository<ID, T> abstractRepository) {
        this.abstractRepository = abstractRepository;
    }


    public List<T> findAll()  {
        return (List<T>) StreamSupport.stream(abstractRepository.findAll().spliterator(),false ).collect(Collectors.toList());
    }

    public void save(T t){
        abstractRepository.saveAndFlush(t);
    }


    public T deleteById(ID Id){

        if(findByID(Id).isPresent()){
            T t = findByID(Id).get();
            abstractRepository.delete(t);
            return t;
        }

        return null;
    }


    public Optional<T> findByID(ID Id){
        return abstractRepository.findById(Id);
    }

}

