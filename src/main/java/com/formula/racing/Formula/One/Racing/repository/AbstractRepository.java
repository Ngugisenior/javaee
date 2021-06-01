package com.formula.racing.Formula.One.Racing.repository;

import com.formula.racing.Formula.One.Racing.domain.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface AbstractRepository <ID extends Serializable, T extends AbstractEntity<ID>> extends JpaRepository<T, ID> {


    @Override
    <S extends T> S saveAndFlush(S s);

    @Override
    void deleteById(ID id);

    @Override
    Optional<T> findById(ID id);

    @Override
    T getOne(ID id);

    @Override
    List<T> findAll();

    @Override
    Page<T> findAll(Pageable pageable);


}
