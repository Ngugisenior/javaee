package com.formula.racing.Formula.One.Racing.services;

import com.formula.racing.Formula.One.Racing.domain.AbstractEntity;
import com.formula.racing.Formula.One.Racing.domain.Progress;
import com.formula.racing.Formula.One.Racing.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService extends AbstractService< Progress,Long> {



    @Autowired
    public ProgressService(AbstractRepository<Long, Progress> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public List<Progress> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Progress> findByID(Long Id) {
        return super.findByID(Id);
    }

}
