package com.formula.racing.Formula.One.Racing.repository;


import com.formula.racing.Formula.One.Racing.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface  PersonRepository extends AbstractRepository<Long, Person>{


    @Override
    @Query("from Person ")
    List<Person> findAll();


    @Query("from Person where first_name=:first_name")
    List<Person> findAllByFirstName(@Param("first_name") String first_name);



    @Override
    Page<Person> findAll(Pageable pageable);

    @Override
    void deleteById(Long aLong);
}
