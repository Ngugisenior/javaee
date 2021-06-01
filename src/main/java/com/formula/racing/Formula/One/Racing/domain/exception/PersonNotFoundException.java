package com.formula.racing.Formula.One.Racing.domain.exception;

import com.formula.racing.Formula.One.Racing.domain.Person;

import java.text.MessageFormat;

public class PersonNotFoundException extends RuntimeException {


    public PersonNotFoundException(Long Id) {
        super(MessageFormat.format("Could not find Person with id: {0}", Id)) ;
    }

}
