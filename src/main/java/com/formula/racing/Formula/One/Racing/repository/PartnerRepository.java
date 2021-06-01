package com.formula.racing.Formula.One.Racing.repository;

import com.formula.racing.Formula.One.Racing.domain.Partner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PartnerRepository extends AbstractRepository<Long, Partner> {


    @Query("select p from Partner p where p.name =?1 ")
    Partner findByName(String name);
}
