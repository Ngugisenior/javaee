package com.formula.racing.Formula.One.Racing.repository;

import com.formula.racing.Formula.One.Racing.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeamRepository extends AbstractRepository<Long, Team>{

    @Override
    @Query("from Team ")
    List<Team> findAll();
}
